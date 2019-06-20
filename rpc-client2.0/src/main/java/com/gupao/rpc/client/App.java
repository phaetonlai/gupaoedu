package com.gupao.rpc.client;

import com.gupao.rpc.client.proxy.RpcClientProxy;
import com.gupao.rpc.server.api.ICalcService;
import com.gupao.rpc.server.api.IHelloService;

/**
 * @Author laihui
 * @Date 2019/6/20
 * @Desc
 * @Version 1.0
 **/
public class App {
    public static void main(String[] args) {
        IHelloService helloService = RpcClientProxy.newInstance(IHelloService.class, "127.0.0.1", 80, "1.1");
        System.out.println(helloService.sayHello("Mic"));

        ICalcService calcService = RpcClientProxy.newInstance(ICalcService.class, "127.0.0.1", 80);
        calcService.add(3, 9);
    }
}
