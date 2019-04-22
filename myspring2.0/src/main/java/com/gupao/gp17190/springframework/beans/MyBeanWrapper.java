package com.gupao.gp17190.springframework.beans;

/**
 * @Author laihui
 * @Date 2019/4/17
 * @Desc
 * @Version 1.0
 **/
public class MyBeanWrapper {
    private Object wrappedInstance;

    public MyBeanWrapper(Object wrappedInstance) {
        this.wrappedInstance = wrappedInstance;
    }

    public Object getWrappedInstance() {
        return wrappedInstance;
    }

    public Class<?> getWrappedClass() {
        return this.wrappedInstance.getClass();
    }
}
