package com.gupao.gp17190.springframework.aop.aspect;

import com.gupao.gp17190.springframework.aop.intercept.MyMethodInterceptor;
import com.gupao.gp17190.springframework.aop.intercept.MyMethodInvocation;

import java.lang.reflect.Method;

/**
 * @Author laihui
 * @Date 2019/4/29
 * @Desc
 * @Version 1.0
 **/
public class MyMethodBeforeAdviceInterceptor extends MyAbstractAspectAdvice implements MyAdvice, MyMethodInterceptor {

    private MyJoinPoint joinPoint;

    public MyMethodBeforeAdviceInterceptor(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    @Override
    public Object invoke(MyMethodInvocation invocation) throws Throwable {
        this.joinPoint = invocation;
        // 织入前置增强
        before(invocation.getMethod(), invocation.getArguments(), invocation.getThis());
        return invocation.proceed();
    }

    private void before(Method method, Object[] arguments, Object aThis) throws Throwable {
        //
        super.invokeAdviceMethod(this.joinPoint, null, null);
    }
}
