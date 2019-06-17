package com.cpic.rpc.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author laihui
 * @Date 2019/6/6
 * @Desc
 * @Version 1.0
 **/
public class RpcServer {
    private Map<String, Object> serviceRegistry;

    public RpcServer(Map<String, Object> serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }

    /**
     * 发布服务
     */
    public void publish() {
        ServerSocket serverSocket = null;
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        ObjectInputStream ois = null;
        try {
            serverSocket = new ServerSocket(80);
            System.out.println("===========================服务已启动，监听端口：80===========================");
            while (true) {
                Socket socket = serverSocket.accept();
                ois = new ObjectInputStream(socket.getInputStream());
                RpcRequest request = (RpcRequest) ois.readObject();
                executorService.execute(()->{
                    doRequest(request, socket);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ois != null)
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (serverSocket != null)
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            executorService.shutdown();
        }
    }

    private void doRequest(RpcRequest request, Socket socket) {
        System.out.println("开始处理新的请求，线程id:" + Thread.currentThread().getId());
        String serviceName = request.getServiceName();
        if (!serviceRegistry.containsKey(serviceName)) {
            throw new RuntimeException("service not found:" + serviceName);
        }
        String methodName = request.getMethodName();
        Object service = serviceRegistry.get(serviceName);
        try {
            Class<?> serviceClass = service.getClass();

            Object[] args = request.getAgrs();
            Method method = null;
            if (args != null && args.length > 0) {
                Class<?>[] paramTypes = new Class<?>[args.length];
                int i = 0;
                for (Object o : args) {
                    paramTypes[i++] = o.getClass();
                }
                method = serviceClass.getMethod(methodName, paramTypes);
            } else {
                method = serviceClass.getMethod(methodName);
            }

            Object result = method.invoke(service, args);
            if (method.getReturnType() != void.class && method.getReturnType() != Void.class) {
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(result);
                oos.flush();
                oos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("请求处理完毕，线程id:" + Thread.currentThread().getId());
        }
    }

}
