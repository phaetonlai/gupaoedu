package com.gupao.gp17190.springframework.beans.factory.support;

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
    private List<String> registryBeanClasses = new ArrayList<>();

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
        URL url = this.getClass().getResource(scanPackage.replaceAll("\\.", "/"));
        File rootPath = new File(url.getFile());
        for (File file : rootPath.listFiles()) {
            if (file.isDirectory()) {
                doScanner(scanPackage + "\\." + file.getName());
            }
            else {
                if (!file.getName().endsWith(".class")) continue;
                String className = scanPackage + "." + file.getName().replace(".class", "");
                // 注册className
                registryBeanClasses.add(className);
            }
        }

    }

    public List<MyBeanDefinition> loadBeanDefinitions() {
        for (String beanClass : this.registryBeanClasses) {
            Class<?> clazz = null;
            try {
                clazz = Class.forName(beanClass);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public int loadBeanDefinitions(String location) {
        return 0;
    }
}
