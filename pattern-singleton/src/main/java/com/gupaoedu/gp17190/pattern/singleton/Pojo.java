package com.gupaoedu.gp17190.pattern.singleton;

import java.io.Serializable;

public class Pojo implements Serializable {
    private static Pojo ourInstance = null;

    static {
        ourInstance = new Pojo();
    }

    public static Pojo getInstance() {
        return ourInstance;
    }

    private Pojo() {
        if (ourInstance != null) {
            throw new RuntimeException("不可重复创建单例对象");
        }
    }

    // 可以被序列化的单例对象，需要重写readResolve方法，从而避免反序列化破坏单例
    private Object readResolve() {
        return ourInstance;
    }
}
