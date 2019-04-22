package com.gupao.gp17190.springframework.context.support;

import com.gupao.gp17190.springframework.beans.MyBeanWrapper;
import com.gupao.gp17190.springframework.beans.annotation.MyAutoWired;
import com.gupao.gp17190.springframework.beans.annotation.MyController;
import com.gupao.gp17190.springframework.beans.annotation.MyService;
import com.gupao.gp17190.springframework.beans.factory.MyBeanFactory;
import com.gupao.gp17190.springframework.beans.factory.config.MyBeanDefinition;
import com.gupao.gp17190.springframework.beans.factory.config.MyBeanPostProcessor;
import com.gupao.gp17190.springframework.beans.factory.support.MyBeanDefinitionReader;
import com.gupao.gp17190.springframework.beans.factory.support.MyDefaultListableBeanFactory;

import java.lang.reflect.Field;
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

    // 单例实例的容器
    private Map<String, Object> singletonObjects = new ConcurrentHashMap(64);

    // IoC容器：beanName-->BeanWrapper
    private Map<String, MyBeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<String, MyBeanWrapper>(256);

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

    public void refresh() throws Exception {
        // 1.定位配置
        reader = new MyBeanDefinitionReader(this.configLocations);
        // 2.解析
        List<MyBeanDefinition> beanDefinitionList = reader.loadBeanDefinitions();

        // 3.注册
        doRegisterBeanDefinition(beanDefinitionList);

        // 4.初始化非延迟加载的bean
        doInitNotLazyBean();
    }

    private void doRegisterBeanDefinition(List<MyBeanDefinition> beanDefinitionList) throws Exception {
        for (MyBeanDefinition beanDefinition : beanDefinitionList) {
            if(super.beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName())) {
                throw new Exception("the bean " + beanDefinition.getFactoryBeanName() + " already exists!");
            }
            super.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
        }
    }

    private void doInitNotLazyBean() {
        for (Map.Entry<String, MyBeanDefinition> entry : beanDefinitionMap.entrySet()) {
            if (entry.getValue().isLazyInit()) continue;
            try {
                getBean(entry.getKey());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Object getBean(String beanName) throws Exception {
        MyBeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);
        Object bean = null;

        // 前置处理
        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        beanPostProcessor.postProcessBeforeInitialization(bean, beanName);

        bean = instantiateBean(beanName, beanDefinition);

        // 把bean实例包装到BeanWrapper中
        MyBeanWrapper beanWrapper = new MyBeanWrapper(bean);
        // 将包装器放入IoC容器
        this.factoryBeanInstanceCache.put(beanName, beanWrapper);

        // 后置处理
        beanPostProcessor.postProcessAfterInitialization(bean, beanName);

        // 注入
        populateBean(beanName, null, beanWrapper);

        return this.factoryBeanInstanceCache.get(beanName).getWrappedInstance();
    }

    private void populateBean(String beanName, MyBeanDefinition myBeanDefinition, MyBeanWrapper beanWrapper) {
        Class<?> beanClass = beanWrapper.getWrappedClass();
        if (!beanClass.isAnnotationPresent(MyController.class)
                && beanClass.isAnnotationPresent(MyService.class))
            return;

        Field[] fields = beanClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(MyAutoWired.class)) continue;

            MyAutoWired autoWired = field.getAnnotation(MyAutoWired.class);
            String dependsBeanName = autoWired.value();
            if ("".equals(dependsBeanName.trim())) {
                dependsBeanName = field.getType().getName();
            }

            Object dependsBeanInstance = this.factoryBeanInstanceCache.get(dependsBeanName);

            // TODO 依赖的bean正在初始化，或者存在循环引用，暂不处理
            // spring中的解决方案是，提前暴露初始化中的实例，来中断深层次的循环
            if (dependsBeanInstance == null) {
                continue;
            }

            try {
                field.setAccessible(true);
                field.set(beanWrapper.getWrappedInstance(), dependsBeanInstance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    private Object instantiateBean(String beanName, MyBeanDefinition beanDefinition) throws Exception {
        // 暂时只支持通过无参的构造器实例化
        Object instance = null;
        if (beanDefinition.isSingleton()) {
            // 如果已经生成了单例bean，直接返回
            if (singletonObjects.containsKey(beanName)) {
                return singletonObjects.get(beanName);
            }

            Class<?> beanClass = Class.forName(beanDefinition.getBeanClassName());
            instance = beanClass.newInstance();

            this.singletonObjects.put(beanName, instance);
            this.singletonObjects.put(beanDefinition.getBeanClassName(), instance);

            return instance;
        }
        // 原型的对象，克隆一个返回
        else if (beanDefinition.isPrototype()) {
            Class<?> beanClass = Class.forName(beanDefinition.getBeanClassName());
            instance = beanClass.newInstance();

            return instance;
        }
        // 指定scope的对象的创建暂不实现

        return instance;
    }

    public <T> T getBean(Class<T> requiredType) {
        return null;
    }

    public <T> T getBean(String beanName, Class<T> requiredType) {
        return null;
    }

    public boolean containsBean(String name) {
        return false;
    }

    public boolean isSingleton(String name) {
        return false;
    }

    public boolean isPrototype(String name) {
        return false;
    }

    public boolean isTypeMatch(String name, Class<?> typeToMatch) {
        return false;
    }

    public Class<?> getType(String name) {
        return null;
    }

    public String[] getAliases(String name) {
        return new String[0];
    }
}
