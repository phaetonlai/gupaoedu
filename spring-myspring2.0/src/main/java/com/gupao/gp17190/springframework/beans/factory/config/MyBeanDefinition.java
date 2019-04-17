package com.gupao.gp17190.springframework.beans.factory.config;

/**
 * @Author laihui
 * @Date 2019/4/16
 * @Desc
 * @Version 1.0
 **/
public class MyBeanDefinition {

    private static final String SCOPE_SINGLETON = "singleton";

    private static final String SCOPE_PROTOTYPE = "prototype";

    public static final String SCOPE_DEFAULT = "singleton";

    public static final int AUTOWIRE_NO = 0;

    public static final int AUTOWIRE_BY_NAME = 1;

    public static final int AUTOWIRE_BY_TYPE = 2;

    public static final int AUTOWIRE_CONSTRUCTOR = 3;

    // 无需依赖校验
    public static final int DEPENDENCY_CHECK_NONE = 0;

    // 对象引用依赖校验
    public static final int DEPENDENCY_CHECK_OBJECTS = 1;

    // 普通属性依赖校验
    public static final int DEPENDENCY_CHECK_SIMPLE = 2;

    // 所有依赖校验
    public static final int DEPENDENCY_CHECK_ALL = 3;

    private volatile Object beanClass;

    private String scope = SCOPE_DEFAULT;

    private boolean abstractFlag = false;

    private boolean lazyInit = false;

    private String[] dependsOn;

    // bean在工厂中的bean name
    private String factoryBeanName;

    private String beanClassName;

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    private String factoryMethodName;

    private boolean synthetic = false;

    public Object getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Object beanClass) {
        this.beanClass = beanClass;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public boolean isAbstractFlag() {
        return abstractFlag;
    }

    public void setAbstractFlag(boolean abstractFlag) {
        this.abstractFlag = abstractFlag;
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public String[] getDependsOn() {
        return dependsOn;
    }

    public void setDependsOn(String[] dependsOn) {
        this.dependsOn = dependsOn;
    }

    public String getFactoryBeanName() {
        return factoryBeanName;
    }

    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }

    public String getFactoryMethodName() {
        return factoryMethodName;
    }

    public void setFactoryMethodName(String factoryMethodName) {
        this.factoryMethodName = factoryMethodName;
    }

    public boolean isSynthetic() {
        return synthetic;
    }

    public void setSynthetic(boolean synthetic) {
        this.synthetic = synthetic;
    }

    public boolean isSingleton() {
        return SCOPE_SINGLETON.equals(scope);
    }

    public boolean isPrototype() {
        return SCOPE_PROTOTYPE.equals(scope);
    }
}
