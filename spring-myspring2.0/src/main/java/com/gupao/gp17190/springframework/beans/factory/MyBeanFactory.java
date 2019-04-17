package com.gupao.gp17190.springframework.beans.factory;

public interface MyBeanFactory {
    // 根据beanName获取bean实例
    Object getBean(String beanName);

    // 根据类型获取bean实例
    <T> T getBean(Class<T> requiredType);

    // 根据beanName获取bean实例，增加了类型验证
    <T> T getBean(String beanName, Class<T> requiredType);

    // 判断容器中是否存在指定名称的bean
    boolean containsBean(String name);

    // 判断bean的scope是否是单例
    boolean isSingleton(String name);

    // 判断bean的scope是否是原型
    boolean isPrototype(String name);

    // 判断指定beanName的bean是否符合类型
    boolean isTypeMatch(String name, Class<?> typeToMatch);

    // 获取指定beanName的bean的类型
    Class<?> getType(String name);

    // 根据beanName获取别名集合
    String[] getAliases(String name);
}
