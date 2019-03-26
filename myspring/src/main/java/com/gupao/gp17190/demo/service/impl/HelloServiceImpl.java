package com.gupao.gp17190.demo.service.impl;

import com.gupao.gp17190.demo.service.IHelloService;
import com.gupao.gp17190.mvcframework.annotation.MyService;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/3/26
 * @description
 **/
@MyService("helloService")
public class HelloServiceImpl implements IHelloService {

    @Override
    public String hello(String name) {
        return "Hello," + name + "!";
    }
}
