package com.gupao.gp17190.pattern.proxy.dynamic.myproxy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class MyClassLoader extends ClassLoader {
    private File basePath;

    public MyClassLoader(){
        String basePathStr = MyClassLoader.class.getResource("").getPath();
        basePath = new File(basePathStr);
    }

    @Override
    public Class<?> findClass(String className) {
        String classFullName = MyClassLoader.class.getPackage().getName() + "." + className;
        if (basePath != null) {
//            File classFile = new File(classFullName.replaceAll("\\.", "\\\\") + ".class");
            File classFile = new File("D:\\IDEA_SPACE\\gupaoedu-vip-pattern-prototype\\pattern-proxy\\target\\classes\\com\\gupao\\gp17190\\pattern\\proxy\\dynamic\\myproxy\\$Proxy0.class");
            if (classFile.exists()) {
                FileInputStream fis = null;
                ByteArrayOutputStream baos = null;
                try {
                    fis = new FileInputStream(classFile);
                    baos = new ByteArrayOutputStream();
                    byte[] buff = new byte[1024];
                    int len;
                    while((len = fis.read(buff)) != -1) {
                        baos.write(buff, 0, len);
                    }
                    return defineClass(classFullName, baos.toByteArray(), 0, baos.size());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
