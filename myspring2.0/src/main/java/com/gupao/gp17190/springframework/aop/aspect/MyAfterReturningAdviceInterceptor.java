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
public class MyAfterReturningAdviceInterceptor extends MyAbstractAspectAdvice implements MyAdvice, MyMethodInterceptor {

    private MyJoinPoint joinPoint;

    public MyAfterReturningAdviceInterceptor(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    @Override
    public Object invoke(MyMethodInvocation invocation) throws Throwable {
        this.joinPoint = invocation;
        Object returnVal = invocation.proceed();

        // 织入后置增强
        afterReturning(returnVal, invocation.getMethod(),
                invocation.getArguments(), invocation.getThis());

        return returnVal;
    }

    private void afterReturning(Object returnVal, Method method,
                                Object[] arguments, Object aThis) throws Throwable {
        super.invokeAdviceMethod(this.joinPoint, returnVal, null);
    }
}
