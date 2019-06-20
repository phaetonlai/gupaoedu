package com.gupao.rpc.server.provider;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author laihui
 * @Date 2019/6/6
 * @Desc
 * @Version 1.0
 **/
public class RpcServer implements ApplicationContextAware, InitializingBean {
    private Map<String, Object> serviceRegistry = new HashMap<String, Object>();

    private int port;

    public RpcServer(int port) {
        this.port = port;
    }

    /**
     * 容器初始化完成后，启动服务端
     * @throws Exception
     */
    public void afterPropertiesSet() throws Exception {
        ServerSocket serverSocket = null;
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("===========================服务已启动，监听端口：" + port + "===========================");
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.execute(new RpcRequestHandler(socket, serviceRegistry));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null)
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            executorService.shutdown();
        }
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(RpcService.class);
        for (Map.Entry<String, Object> entry : beanMap.entrySet()) {
            Object serviceBean = entry.getValue();
            RpcService rpcService = serviceBean.getClass().getAnnotation(RpcService.class);
            Class<?> serviceItfs = rpcService.value();
            String version = rpcService.version();
            String serviceName = serviceItfs.getName();
            if (!StringUtils.isEmpty(version)) {
                serviceName = serviceName + "_" + version;
            }

            serviceRegistry.put(serviceName, serviceBean);
        }

    }
}
