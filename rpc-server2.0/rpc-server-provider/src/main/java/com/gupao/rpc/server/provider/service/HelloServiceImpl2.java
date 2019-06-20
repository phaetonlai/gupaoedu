package com.gupao.rpc.server.provider.service;

import com.gupao.rpc.server.api.IHelloService;
import com.gupao.rpc.server.provider.RpcService;

/**
 * @Author laihui
 * @Date 2019/6/20
 * @Desc
 * @Version 1.0
 **/
@RpcService(value = IHelloService.class, version = "1.1")
public class HelloServiceImpl2 implements IHelloService {
    public String sayHello(String name) {
        return "【version 1.1】Hello," + name + "!";
    }
}
