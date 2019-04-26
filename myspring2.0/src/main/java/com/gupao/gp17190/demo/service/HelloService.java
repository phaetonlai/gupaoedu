package com.gupao.gp17190.demo.service;

import com.gupao.gp17190.springframework.beans.annotation.MyService;

/**
 * @Author laihui
 * @Date 2019/4/25
 * @Desc
 * @Version 1.0
 **/
@MyService("helloService")
public class HelloService {
    public String hello() {
        return "hello";
    }
}
