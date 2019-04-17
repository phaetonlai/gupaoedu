package com.gupao.gp17190.springframework.beans.factory;

public interface MyFactoryBean<T> {
    // 获取容器管理的对象实例
    T getObject();

    // 获取对象创建的对象类型
    Class<?> getObjectType();

    // 判断容器管理的对象是否是单例
    boolean isSingleton();
}
