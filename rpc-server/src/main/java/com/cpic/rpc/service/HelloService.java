package com.cpic.rpc.service;

/**
 * @Author laihui
 * @Date 2019/6/6
 * @Desc
 * @Version 1.0
 **/
public class HelloService implements IHelloService {
    public String sayHello(String name) {
        return "Hello, " + name;
    }
}
