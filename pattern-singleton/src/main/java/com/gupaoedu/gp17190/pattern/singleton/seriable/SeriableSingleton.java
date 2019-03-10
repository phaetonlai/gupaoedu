package com.gupaoedu.gp17190.pattern.singleton.seriable;

import java.io.Serializable;

public class SeriableSingleton implements Serializable {
    private static final SeriableSingleton INSTANCE = new SeriableSingleton();

    private SeriableSingleton() {
        // 阻止暴力反射破坏单例
        if(INSTANCE != null)
            throw new RuntimeException("单例对象只能被创建一次");
    }

    public static SeriableSingleton getInstance() {
        return INSTANCE;
    }

    // 阻止反序列化破坏单例
    private Object readResolve() {
        return INSTANCE;
    }
}
