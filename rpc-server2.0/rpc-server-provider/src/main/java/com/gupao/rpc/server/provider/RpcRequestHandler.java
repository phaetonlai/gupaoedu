package com.gupao.rpc.server.provider;

import com.gupao.rpc.server.api.RpcRequest;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 * @Author laihui
 * @Date 2019/6/20
 * @Desc
 * @Version 1.0
 **/
public class RpcRequestHandler implements Runnable {
    private Socket socket;

    private Map<String, Object> serviceRegistry;

    public RpcRequestHandler(Socket socket, Map<String, Object> serviceRegistry) {
        this.socket = socket;
        this.serviceRegistry = serviceRegistry;
    }

    public void run() {
        doRequest();
    }

    private void doRequest() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            RpcRequest request = (RpcRequest) ois.readObject();
            System.out.println("开始处理新的请求，线程id:" + Thread.currentThread().getId());
            String serviceName = request.getServiceName();
            if(!StringUtils.isEmpty(request.getVersion())) {
                serviceName = serviceName + "_" + request.getVersion();
            }
            if (!serviceRegistry.containsKey(serviceName)) {
                throw new RuntimeException("service not found:" + serviceName);
            }
            Object service = serviceRegistry.get(serviceName);

            Class<?> serviceClass = service.getClass();

            Method method = serviceClass.getMethod(request.getMethodName(), request.getParamTypes());

            Object result = method.invoke(service, request.getAgrs());
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
                if (ois != null) ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("请求处理完毕，线程id:" + Thread.currentThread().getId());
        }
    }
}
