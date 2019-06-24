package com.gupao.rpc.client.proxy;

import com.gupao.rpc.server.api.RpcProtocol;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author laihui
 * @Date 2019/6/20
 * @Desc
 * @Version 1.0
 **/
public class RpcInvocationHandler implements InvocationHandler {
    private String ip;

    private int port;

    private String version;

    public RpcInvocationHandler(String ip, int port, String version) {
        this.ip = ip;
        this.port = port;
        this.version = version;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcProtocol request = new RpcProtocol();
        request.setClassName(proxy.getClass().getInterfaces()[0].getName());
        request.setMethodName(method.getName());
        request.setParamTypes(method.getParameterTypes());
        request.setParams(args);
        request.setVersion(version);

        return RpcRequestSender.send(ip, port, request);
    }
}
