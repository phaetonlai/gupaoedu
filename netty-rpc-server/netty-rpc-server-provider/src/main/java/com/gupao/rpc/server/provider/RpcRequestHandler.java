package com.gupao.rpc.server.provider;

import com.gupao.rpc.server.api.RpcProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Author laihui
 * @Date 2019/6/20
 * @Desc
 * @Version 1.0
 **/
public class RpcRequestHandler extends ChannelInboundHandlerAdapter {

    private Map<String, Object> serviceRegistry;

    public RpcRequestHandler(Map<String, Object> serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RpcProtocol request = (RpcProtocol) msg;
        System.out.println("开始处理新的请求，线程:" + Thread.currentThread().getName());
        String serviceName = request.getClassName();
        if(!StringUtils.isEmpty(request.getVersion())) {
            serviceName = serviceName + "_" + request.getVersion();
        }
        if (!serviceRegistry.containsKey(serviceName)) {
            throw new RuntimeException("service not found:" + serviceName);
        }
        Object service = serviceRegistry.get(serviceName);

        Class<?> serviceClass = service.getClass();

        Method method = serviceClass.getMethod(request.getMethodName(), request.getParamTypes());

        Object result = method.invoke(service, request.getParams());

        if ((method.getReturnType() != Void.class) && (method.getReturnType() != void.class)) {
            ctx.write(result);
            ctx.flush();
        }
        ctx.close();

        System.out.println("请求处理完毕，线程:" + Thread.currentThread().getName());
    }

}
