package com.gupao.gp17190.springframework.beans.factory.support;

import com.gupao.gp17190.springframework.beans.factory.config.MyBeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author laihui
 * @Date 2019/4/17
 * @Desc
 * @Version 1.0
 **/
public class MyDefaultListableBeanFactory {
    protected Map<String, MyBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, MyBeanDefinition>();
}
