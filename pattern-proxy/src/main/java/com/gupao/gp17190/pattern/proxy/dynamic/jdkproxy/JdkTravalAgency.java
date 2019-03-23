package com.gupao.gp17190.pattern.proxy.dynamic.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkTravalAgency implements InvocationHandler {

    private Object target;

    public Object getInstance(Object obj) {
        this.target = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target, args);
        after();
        return result;
    }

    private void before() {
        System.out.println("告诉我你想去哪，我来安排。。。");
    }

    private void after() {
        System.out.println("旅行完美结束，记得给好评哦！！！");
    }
}
