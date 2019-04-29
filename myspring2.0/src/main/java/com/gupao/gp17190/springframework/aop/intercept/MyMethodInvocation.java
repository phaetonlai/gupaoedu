package com.gupao.gp17190.springframework.aop.intercept;

import com.gupao.gp17190.springframework.aop.aspect.MyJoinPoint;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author laihui
 * @Date 2019/4/28
 * @Desc
 * @Version 1.0
 **/
public class MyMethodInvocation implements MyJoinPoint {

    private Object proxy;

    private Method method;

    private Object target;

    private Object[] arguments;

    private List<Object> interceptorsAndDynamicMethodMatchers;

    private Class<?> targetClass;

    private Map<String, Object> userAttributes;

    private int currentInterceptorIndex = -1;

    public MyMethodInvocation(Object proxy, Method method,
                              Object target, Object[] arguments,
                              List<Object> interceptorsAndDynamicMethodMatchers,
                              Class<?> targetClass) {
        this.proxy = proxy;
        this.method = method;
        this.target = target;
        this.arguments = arguments;
        this.interceptorsAndDynamicMethodMatchers = interceptorsAndDynamicMethodMatchers;
        this.targetClass = targetClass;
    }

    public Object proceed() throws Throwable {
        // 最后执行被增强的方法
        if (this.currentInterceptorIndex == interceptorsAndDynamicMethodMatchers.size() - 1) {
            return this.method.invoke(this.target, this.arguments);
        }

        Object interceptorOrInterceptionAdvice = this.interceptorsAndDynamicMethodMatchers.get(++this.currentInterceptorIndex);
        // 执行织入的逻辑
        if (interceptorOrInterceptionAdvice instanceof MyMethodInterceptor) {
            MyMethodInterceptor interceptor = (MyMethodInterceptor) interceptorOrInterceptionAdvice;
            return interceptor.invoke(this);
        }

        return proceed();
    }

    @Override
    public Object getThis() {
        return this.target;
    }

    @Override
    public Object[] getArguments() {
        return this.arguments;
    }

    @Override
    public Method getMethod() {
        return this.method;
    }

    @Override
    public void setUserAttribute(String key, Object value) {
        if (value != null) {
            if (this.userAttributes == null) {
                this.userAttributes = new HashMap<>();
            }
            this.userAttributes.put(key, value);
        }
        else {
            if (this.userAttributes != null) this.userAttributes.remove(key);
        }
    }

    @Override
    public Object getUserAttribute(String key) {
        return this.userAttributes == null ? null : this.userAttributes.get(key);
    }
}
