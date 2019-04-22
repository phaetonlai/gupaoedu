package com.gupao.gp17190.springframework.beans.factory.config;

/**
 * @Author laihui
 * @Date 2019/4/18
 * @Desc
 * @Version 1.0
 **/
public class MyBeanPostProcessor {
    // 在bean初始化前提供回调入口
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    // 在bean初始化后提供回调入口
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }
}
