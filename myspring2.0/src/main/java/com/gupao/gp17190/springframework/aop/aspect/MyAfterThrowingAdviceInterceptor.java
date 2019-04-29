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
public class MyAfterThrowingAdviceInterceptor extends MyAbstractAspectAdvice implements MyAdvice, MyMethodInterceptor {

    private String throwingName;

    public MyAfterThrowingAdviceInterceptor(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    @Override
    public Object invoke(MyMethodInvocation invocation) throws Throwable {
        try {
            return invocation.proceed();
        } catch (Throwable throwable) {
            // 异常时织入异常增强
            super.invokeAdviceMethod(invocation, null, throwable.getCause());
            throw throwable;
        }
    }

    public void setThrowingName(String throwingName) {
        this.throwingName = throwingName;
    }
}
