package com.gupao.rpc.client.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author laihui
 * @Date 2019/6/20
 * @Desc
 * @Version 1.0
 **/
public class RpcClientProxy {
    public static <T> T newInstance(Class<T> clazz, String ip, int port) {
        return newInstance(clazz, ip, port, "");
    }

    public static <T> T newInstance(Class<T> clazz, String ip, int port, String version) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[] {clazz}, new RpcInvocationHandler(ip, port, version));
    }
}
