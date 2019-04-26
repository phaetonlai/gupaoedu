package com.gupao.gp17190.springframework.beans.factory.support;

import com.gupao.gp17190.springframework.beans.annotation.MyController;
import com.gupao.gp17190.springframework.beans.annotation.MyService;
import com.gupao.gp17190.springframework.beans.factory.config.MyBeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Author laihui
 * @Date 2019/4/16
 * @Desc
 * @Version 1.0
 **/
public class MyBeanDefinitionReader {
    // 已经注册的bean的class名称
    private List<String> registryBeanClasses = new ArrayList<String>();

    private Properties config = new Properties();

    private final String SCAN_PACKAGE = "scanPackage";

    public MyBeanDefinitionReader() {
    }

    public MyBeanDefinitionReader(String[] configLocations) {
        // 加载配置文件进内存
        // 此处简化处理，只解析第一个路径
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(configLocations[0].replace("classpath:", ""));
        try {
            config.load(is);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 扫描包下的class
        doScanner(this.config.getProperty(SCAN_PACKAGE));
    }

    private void doScanner(String scanPackage) {
        URL url = this.getClass().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File rootPath = new File(url.getFile());
        for (File file : rootPath.listFiles()) {
            if (file.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
            }
            else {
                if (!file.getName().endsWith(".class")) continue;
                String className = scanPackage + "." + file.getName().replace(".class", "");
                // 注册className，未加注解的类，不在管理范围
                try {
                    Class<?> beanClass = Class.forName(className);
                    boolean needInstantiate = beanClass.isAnnotationPresent(MyService.class)
                            || beanClass.isAnnotationPresent(MyController.class);
                    if (!needInstantiate) continue;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                registryBeanClasses.add(className);
            }
        }

    }

    public List<MyBeanDefinition> loadBeanDefinitions() {
        List<MyBeanDefinition> beanDefinitions = new ArrayList<MyBeanDefinition>();
        // 扫描已经注册的类信息，创建对应的BeanDefinition
        for (String beanClass : this.registryBeanClasses) {
            Class<?> clazz = null;
            try {
                clazz = Class.forName(beanClass);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (clazz.isInterface()) continue;

            beanDefinitions.add(doCreateBeanDefinition(toLowerFirstCase(clazz.getSimpleName()), clazz.getName()));
            beanDefinitions.add(doCreateBeanDefinition(clazz.getName(), clazz.getName()));

            // 接口
            for (Class<?> anInterface : clazz.getInterfaces()) {
                beanDefinitions.add(doCreateBeanDefinition(anInterface.getName(), clazz.getName()));
            }

        }

        return beanDefinitions;
    }

    // 创建BeanDefinition
    private MyBeanDefinition doCreateBeanDefinition(String beanName, String clazzName) {
        MyBeanDefinition beanDefinition = new MyBeanDefinition();
        beanDefinition.setBeanClassName(clazzName);
        beanDefinition.setFactoryBeanName(beanName);

        return beanDefinition;
    }

    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        if ((chars[0] >= 'A') && (chars[0] <= 'Z'))
            chars[0] += 32;

        return String.valueOf(chars);
    }

    public int loadBeanDefinitions(String location) {
        return 0;
    }

    public Properties getConfig() {
        return this.config;
    }
}
