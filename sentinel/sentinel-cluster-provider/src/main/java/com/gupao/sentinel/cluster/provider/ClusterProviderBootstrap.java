package com.gupao.sentinel.cluster.provider;

import com.gupao.sentinel.cluster.provider.config.DubboConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

/**
 * @Author laihui
 * @Date 2019/8/13
 * @Desc
 * @Version 1.0
 **/
public class ClusterProviderBootstrap {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(DubboConfiguration.class);
        context.refresh();
        System.in.read();
    }
}
