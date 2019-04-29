package com.gupao.gp17190.springframework.aop.intercept;

public interface MyMethodInterceptor {
    Object invoke(MyMethodInvocation invocation) throws Throwable;
}
