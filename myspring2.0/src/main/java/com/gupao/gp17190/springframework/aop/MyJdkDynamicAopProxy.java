package com.gupao.gp17190.springframework.aop;

import com.gupao.gp17190.springframework.aop.intercept.MyMethodInvocation;
import com.gupao.gp17190.springframework.aop.support.MyAdvisedSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @Author laihui
 * @Date 2019/4/29
 * @Desc
 * @Version 1.0
 **/
public class MyJdkDynamicAopProxy implements MyAopProxy, InvocationHandler {
    private MyAdvisedSupport support;

    public MyJdkDynamicAopProxy(MyAdvisedSupport support) {
        this.support = support;
    }

    @Override
    public Object getProxy() {
        return getProxy(this.support.getTargetClass().getClassLoader());
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return Proxy.newProxyInstance(classLoader, this.support.getTargetClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<Object> interceptorsAndDynamicInterceptionAdvise = this.support.getInterceptorsAndDynamicInterceptionAdvise(method, this.support.getTargetClass());
        MyMethodInvocation invocation = new MyMethodInvocation(proxy, method,
                this.support.getTarget(), args,
                interceptorsAndDynamicInterceptionAdvise,
                this.support.getTargetClass());

        return invocation.proceed();
    }
}
