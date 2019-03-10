package com.gupaoedu.gp17190.pattern.singleton.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContainerSingleton {
    public static final Map<String, Object> HOLDER = new ConcurrentHashMap<String, Object>();

    private ContainerSingleton() {}

    public static synchronized Object getInstance(String className) {
        if (!HOLDER.containsKey(className)) {
            try {
                Class clazz = Class.forName(className);
                Object v = clazz.newInstance();
                HOLDER.put(className, v);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return HOLDER.get(className);
    }
}
