package com.gupao.gp17190.springframework.aop;

public interface MyAopProxy {
    Object getProxy();

    Object getProxy(ClassLoader classLoader);
}
