package com.gupao.gp17190.springframework.beans.factory.support;

import com.gupao.gp17190.springframework.beans.factory.config.MyBeanDefinition;
import com.gupao.gp17190.springframework.context.support.MyAbstractApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author laihui
 * @Date 2019/4/17
 * @Desc
 * @Version 1.0
 **/
public class MyDefaultListableBeanFactory extends MyAbstractApplicationContext {
    protected final Map<String, MyBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, MyBeanDefinition>();

    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }
}
