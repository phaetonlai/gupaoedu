package com.gupao.rpc.server.provider.service;

import com.gupao.rpc.server.api.IHelloService;
import com.gupao.rpc.server.provider.RpcService;

/**
 * @Author laihui
 * @Date 2019/6/20
 * @Desc
 * @Version 1.0
 **/
@RpcService(value = IHelloService.class)
public class HelloServiceImpl implements IHelloService {
    public String sayHello(String name) {
        return "Hello," + name + "!";
    }
}
