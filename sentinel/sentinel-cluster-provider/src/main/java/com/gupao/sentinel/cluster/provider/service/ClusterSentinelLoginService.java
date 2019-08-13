package com.gupao.sentinel.cluster.provider.service;

import com.gupao.dubbo.rpc.service.api.ILoginService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @Author laihui
 * @Date 2019/8/12
 * @Desc
 * @Version 1.0
 **/
@Service
public class ClusterSentinelLoginService implements ILoginService {

    public String login(String name) {
        return "the service comes from sentinel cluster, " + name + " login success!";
    }
}
