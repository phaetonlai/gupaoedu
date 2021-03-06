package com.gupao.gp17190.springframework.aop.aspect;

import java.lang.reflect.Method;

public interface MyJoinPoint {
    Object getThis();

    Object[] getArguments();

    Method getMethod();

    void setUserAttribute(String key, Object value);

    Object getUserAttribute(String key);
}
