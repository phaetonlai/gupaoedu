package com.gupaoedu.gp17190.pattern.singleton.lazy;

public class LazySimpleSingleton {
    private static volatile LazySimpleSingleton instance = null;

    private LazySimpleSingleton() {
        // 阻止暴力反射破坏单例
        if(instance != null)
            throw new RuntimeException("单例对象只能被创建一次");
    }

    public static LazySimpleSingleton getInstance() {
        // 双重检查锁，确保单例创建过程的线程a安全
        if (instance == null) {
            synchronized (LazySimpleSingleton.class) {
                if (instance == null) {
                    instance = new LazySimpleSingleton();
                }
            }
        }
        return instance;
    }
}
