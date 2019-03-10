package com.gupaoedu.gp17190.pattern.singleton;

import com.gupaoedu.gp17190.pattern.singleton.register.EnumSingleton;

import java.io.*;
import java.lang.reflect.Constructor;

public class SingletonTest {
    public static void main(String[] args) throws Exception {
//        Class clazz = Pojo.class;
//        Constructor constructor = clazz.getDeclaredConstructor(null);
//        constructor.setAccessible(true);
//        Pojo o1 = (Pojo) constructor.newInstance();
//          Pojo o2 = Pojo.getInstance();
//
//        System.out.println(o1);
//        System.out.println(o2);
//        System.out.println(o1 == o2);
//          Pojo o2 = Pojo.getInstance();
////
////          ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("D:\\IDEA_SPACE\\Pojo.io")));
////          oos.writeObject(o2);
////          oos.flush();
////          oos.close();
////
////          ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("D:\\IDEA_SPACE\\Pojo.io")));
////          Object o1 = ois.readObject();
////          ois.close();
////          System.out.println(o1);
////          System.out.println(o2);


          EnumSingleton o2 = EnumSingleton.getInstance();

          ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("D:\\IDEA_SPACE\\EnumSingleton.io")));
          oos.writeObject(o2);
          oos.flush();
          oos.close();

          ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("D:\\IDEA_SPACE\\EnumSingleton.io")));
          Object o1 = ois.readObject();
          ois.close();
          System.out.println(o1);
          System.out.println(o2);

          Class clazz = EnumSingleton.class;
          Constructor c = clazz.getDeclaredConstructor(String.class, int.class);
          c.setAccessible(true);
          Object o3 = c.newInstance("SINGLETON", 0);// Constructor中会判断class的描述符中是否有枚举类型，有则抛出异常
          System.out.println(o3);

    }
}
