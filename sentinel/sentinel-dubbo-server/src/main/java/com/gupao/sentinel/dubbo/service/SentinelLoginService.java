package com.gupao.sentinel.dubbo.service;

import com.gupao.dubbo.rpc.service.api.ILoginService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @Author laihui
 * @Date 2019/8/12
 * @Desc
 * @Version 1.0
 **/
@Service
public class SentinelLoginService implements ILoginService {

    @Override
    public String login(String name) {
        return "the service comes from sentinel, " + name + " login success!";
    }
}
