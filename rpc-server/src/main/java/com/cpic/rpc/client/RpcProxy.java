package com.cpic.rpc.client;

import com.cpic.rpc.server.RpcRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * @Author laihui
 * @Date 2019/6/6
 * @Desc
 * @Version 1.0
 **/
public class RpcProxy {

    public static <T> T newInstance(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class<?>[] {clazz}, new ClientProxy());
    }

    private static class ClientProxy implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            RpcRequest request = new RpcRequest();
            request.setServiceName(proxy.getClass().getInterfaces()[0].getSimpleName());
            request.setMethodName(method.getName());
            request.setAgrs(args);
            Socket socket = null;
            ObjectOutputStream oos = null;
            ObjectInputStream ois = null;
            try {
                socket = new Socket("localhost", 80);
                oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(request);
                oos.flush();
                ois = new ObjectInputStream(socket.getInputStream());
                if (method.getReturnType() != void.class && method.getReturnType() != Void.class) {
                    Object result = ois.readObject();
                    return result;
                }
                return null;
            } finally {
                if (oos != null) oos.close();
                if (ois != null) ois.close();
                if (socket != null) socket.close();
            }
        }
    }
}
