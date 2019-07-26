package com.gupao.dubbo.rpc.client;

import com.gupao.dubbo.rpc.service.api.ILoginService;
import com.gupao.dubbo.rpc.service.api.IRegisterService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author laihui
 * @Date 2019/7/25
 * @Desc
 * @Version 1.0
 **/
public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"application.xml"});
        ILoginService loginService = (ILoginService) context.getBean("loginService");
        System.out.println(loginService.login("Mic"));

        IRegisterService registerService = (IRegisterService) context.getBean("registerService");
        System.out.println(registerService.register("Jack"));

        context.close();
    }
}
