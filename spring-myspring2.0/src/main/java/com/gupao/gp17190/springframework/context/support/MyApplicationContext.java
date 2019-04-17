package com.gupao.gp17190.springframework.context.support;

import com.gupao.gp17190.springframework.beans.MyBeanWrapper;
import com.gupao.gp17190.springframework.beans.factory.MyBeanFactory;
import com.gupao.gp17190.springframework.beans.factory.config.MyBeanDefinition;
import com.gupao.gp17190.springframework.beans.factory.support.MyBeanDefinitionReader;
import com.gupao.gp17190.springframework.beans.factory.support.MyDefaultListableBeanFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author laihui
 * @Date 2019/4/17
 * @Desc
 * @Version 1.0
 **/
public class MyApplicationContext extends MyDefaultListableBeanFactory implements MyBeanFactory {
    private String[] configLocations;

    private MyBeanDefinitionReader reader;

    private Map<String, Object> singletonObjects = new ConcurrentHashMap(64);

    private Map<String, MyBeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<>(256);

    public MyApplicationContext() {
    }

    public MyApplicationContext(String... configLocations) {
        this.configLocations = configLocations;
        try {
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void refresh() {
        // 1.定位配置
        reader = new MyBeanDefinitionReader(this.configLocations);
        // 2.解析
        List<MyBeanDefinition> beanDefinitionList = reader.loadBeanDefinitions();

        // 3.注册

        // 4.初始化非延迟加载的bean

    }

    @Override
    public Object getBean(String beanName) {
        return null;
    }

    @Override
    public <T> T getBean(Class<T> requiredType) {
        return null;
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) {
        return null;
    }

    @Override
    public boolean containsBean(String name) {
        return false;
    }

    @Override
    public boolean isSingleton(String name) {
        return false;
    }

    @Override
    public boolean isPrototype(String name) {
        return false;
    }

    @Override
    public boolean isTypeMatch(String name, Class<?> typeToMatch) {
        return false;
    }

    @Override
    public Class<?> getType(String name) {
        return null;
    }

    @Override
    public String[] getAliases(String name) {
        return new String[0];
    }
}
