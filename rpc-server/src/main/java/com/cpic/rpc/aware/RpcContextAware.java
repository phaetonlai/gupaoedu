package com.cpic.rpc.aware;

import com.cpic.rpc.annotation.RpcService;
import com.cpic.rpc.server.RpcServer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author laihui
 * @Date 2019/6/6
 * @Desc
 * @Version 1.0
 **/
public class RpcContextAware implements ApplicationContextAware, InitializingBean {

    private Map<String, Object> serviceRegistry = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> rpcServices = applicationContext.getBeansWithAnnotation(RpcService.class);
        for (Map.Entry<String, Object> entry : rpcServices.entrySet()) {
            String serviceName = entry.getKey();
            Object serviceBean = entry.getValue();
            RpcService rpcService = serviceBean.getClass().getAnnotation(RpcService.class);
            String version = rpcService.version();
            if (!StringUtils.isEmpty(version)) {
                serviceName = serviceName + "_" + version;
            }

            serviceRegistry.put(serviceName, serviceBean);
        }

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 启动服务
        new RpcServer(serviceRegistry).publish();
    }
}
