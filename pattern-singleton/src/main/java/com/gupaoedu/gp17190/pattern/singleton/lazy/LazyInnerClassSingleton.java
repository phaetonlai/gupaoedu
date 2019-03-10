package com.gupaoedu.gp17190.pattern.singleton.lazy;

public class LazyInnerClassSingleton {
    private LazyInnerClassSingleton() {
        // 阻止暴力反射破坏单例
        if(InnerHolder.INSTANCE != null)
            throw new RuntimeException("单例对象只能被创建一次");
    }

    public static LazyInnerClassSingleton getInstance() {
        return InnerHolder.INSTANCE;
    }

    // 外部类被加载前，会先加载内部类
    // 利用JVM层的类加载顺序，确保在外部类被加载完成时，单例对象已完成创建
    private static class InnerHolder {
        private static final LazyInnerClassSingleton INSTANCE = new LazyInnerClassSingleton();
    }
}
