package com.cpic.rpc.service;

import com.cpic.rpc.annotation.RpcService;

/**
 * @Author laihui
 * @Date 2019/6/6
 * @Desc
 * @Version 1.0
 **/
@RpcService(value = IHelloService.class)
public class HelloService implements IHelloService {
    public String sayHello(String name) {
        return "Hello, " + name;
    }
}
