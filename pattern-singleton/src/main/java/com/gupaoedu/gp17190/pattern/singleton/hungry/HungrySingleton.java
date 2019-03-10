package com.gupaoedu.gp17190.pattern.singleton.hungry;

public class HungrySingleton {
    private static final HungrySingleton SINGLETON = new HungrySingleton();

    public static HungrySingleton getInstance() {
        return SINGLETON;
    }

    private HungrySingleton() {
        if(SINGLETON != null)
            throw new RuntimeException("单例对象只能被创建一次");
    }

}
