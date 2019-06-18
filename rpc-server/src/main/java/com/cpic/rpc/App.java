package com.cpic.rpc;

import com.cpic.rpc.server.RpcServer;
import com.cpic.rpc.service.HelloService;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author laihui
 * @Date 2019/6/6
 * @Desc
 * @Version 1.0
 **/
public class App {
    public static void main(String[] args) {
        Map<String, Object> serviceRegistry = new HashMap<>();
        serviceRegistry.put("IHelloService", new HelloService());

        new RpcServer(serviceRegistry).publish();
    }
}
