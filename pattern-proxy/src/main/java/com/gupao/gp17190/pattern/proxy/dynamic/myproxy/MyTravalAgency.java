package com.gupao.gp17190.pattern.proxy.dynamic.myproxy;

import java.lang.reflect.Method;

public class MyTravalAgency implements MyInvocationHandler {

    private Object target;

    public Object getInstance(Object obj) {
        this.target = obj;
        try {
            return MyProxy.newProxyInstance(new MyClassLoader(), obj.getClass().getInterfaces(), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
