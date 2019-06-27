package com.gupao.netty.tomcat;

import com.gupao.netty.tomcat.http.MyRequest;
import com.gupao.netty.tomcat.http.MyResponse;
import com.gupao.netty.tomcat.http.MyServlet;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author laihui
 * @Date 2019/6/27
 * @Desc
 * @Version 1.0
 **/
public class MyTomcat {
    private int port;

    public MyTomcat(int port) {
        this.port = port;
    }

    private Map<String, MyServlet> servletContainer = new HashMap<String, MyServlet>();

    private Properties webProps = new Properties();

    private void init() {
        try {
            String rootPath = this.getClass().getResource("/").getPath();
            FileInputStream fis = new FileInputStream(rootPath + "web.properties");
            webProps.load(fis);
            for (Map.Entry<Object, Object> entry : webProps.entrySet()) {
                String key = entry.getKey().toString();
                if (key.endsWith(".url")) {
                    String servletName = key.replaceAll("\\.url$", "");
                    String className = webProps.getProperty(servletName + ".className");
                    Class<?> clazz = Class.forName(className);
                    servletContainer.put(webProps.getProperty(key), (MyServlet) clazz.newInstance());
                }
            }
            System.out.println("servlet容器初始化完成。。。");
            System.out.println(servletContainer);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        init();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new HttpResponseEncoder());
                            socketChannel.pipeline().addLast(new HttpRequestDecoder());
                            socketChannel.pipeline().addLast(new MyRequestHandler());
                        }
                    })
                    // 主线程最大线程数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 子线程，保持长连接
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            // 绑定端口
            ChannelFuture future = serverBootstrap.bind(port).sync();
            System.out.println("mini版tomcat启动完毕，监听端口：" + port);
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    private class MyRequestHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            HttpRequest httpRequest = (HttpRequest) msg;
            MyRequest request = new MyRequest(ctx, httpRequest);
            MyResponse response = new MyResponse(ctx, httpRequest);
            String url = request.getUrl();
            if (!servletContainer.containsKey(url)) {
                response.write("404-Not Found!");
            }
            else {
                servletContainer.get(url).service(request, response);
            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        }
    }


    public static void main(String[] args) {
        new MyTomcat(9088).start();
    }

}
