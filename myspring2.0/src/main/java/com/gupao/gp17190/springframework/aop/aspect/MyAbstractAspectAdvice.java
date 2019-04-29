package com.gupao.gp17190.springframework.aop.aspect;

import java.lang.reflect.Method;

/**
 * @Author laihui
 * @Date 2019/4/29
 * @Desc
 * @Version 1.0
 **/
public abstract class MyAbstractAspectAdvice implements MyAdvice {
    private Method aspectMethod;

    private Object aspectTarget;

    public MyAbstractAspectAdvice(Method aspectMethod, Object aspectTarget) {
        this.aspectMethod = aspectMethod;
        this.aspectTarget = aspectTarget;
    }

    public Object invokeAdviceMethod(MyJoinPoint joinPoint, Object returnVal, Throwable tx) throws Throwable {
        Class<?>[] paramTypes = this.aspectMethod.getParameterTypes();
        // 无参，直接调用
        if (paramTypes == null || paramTypes.length == 0)
            return this.aspectMethod.invoke(this.aspectTarget);

        // 有参数
        Object[] args = new Object[paramTypes.length];
        for (int i = 0; i < paramTypes.length; i++) {
            if (paramTypes[i] == MyJoinPoint.class)
                args[i] = joinPoint;
            else if (paramTypes[i] == Throwable.class)
                args[i] = tx;
            else if (paramTypes[i] == Object.class)
                args[i] = returnVal;
        }
        return this.aspectMethod.invoke(this.aspectTarget, args);
    }
}
