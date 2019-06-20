package com.gupao.rpc.server.provider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author laihui
 * @Date 2019/6/20
 * @Desc
 * @Version 1.0
 **/
@Configuration
@ComponentScan(basePackages = {"com.gupao.rpc.server.provider"})
public class SpringConfig {
    @Bean(name = "rpcServer")
    public RpcServer rpcServer() {
        return new RpcServer(80);
    }
}
