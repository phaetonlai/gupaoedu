package com.gupao.gp17190.demo;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/3/26
 * @description
 **/
public class TestMain implements Serializable {
    String name = "";
    Object o = null;
    public static void main(String[] args) {
        Class clazz = TestMain.class;
        for (Class anInterface : clazz.getInterfaces()) {
            System.out.println(anInterface.getName());
            System.out.println(anInterface.getSimpleName());
        }


    }
}
