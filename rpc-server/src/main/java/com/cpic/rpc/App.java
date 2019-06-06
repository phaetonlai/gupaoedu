package com.cpic.rpc;

import com.cpic.rpc.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author laihui
 * @Date 2019/6/6
 * @Desc
 * @Version 1.0
 **/
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        ((AnnotationConfigApplicationContext) context).start();
    }
}
