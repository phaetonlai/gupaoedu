package com.gupao.sentinel.cluster.provider;

import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientAssignConfig;
import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientConfig;
import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientConfigManager;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;

/**
 * @Author laihui
 * @Date 2019/8/13
 * @Desc
 * @Version 1.0
 **/
public class DataSourceInitFunc implements InitFunc {
    private static final String TOKEN_SERVER_ADDR = "localhost";
    private static final int TOKEN_SERVER_PORT = 9090;
    private static final int REQ_TIME_OUT = 200000;
    private static final String APP_NAME = "App-Phae";

    private static final String NACOS_SERVER_ADDR = "localhost";
    private static final String GROUP_ID = "SENTINEL_GROUP";
    private static final String FLOW_SUFFIX = "-flow-rules";

    public void init() throws Exception {
        loadClusterClientConfig();
        registerClusterFlowRulePropertiy();
    }

    private void registerClusterFlowRulePropertiy() {
        ReadableDataSource<String, List<FlowRule>> dataSource =
                new NacosDataSource<>(NACOS_SERVER_ADDR, GROUP_ID, APP_NAME + FLOW_SUFFIX,
                        source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>(){}));

        FlowRuleManager.register2Property(dataSource.getProperty());
    }

    private void loadClusterClientConfig() {
        ClusterClientAssignConfig assignConfig = new ClusterClientAssignConfig();
        assignConfig.setServerHost(TOKEN_SERVER_ADDR);
        assignConfig.setServerPort(TOKEN_SERVER_PORT);

        ClusterClientConfig clientConfig = new ClusterClientConfig();
        clientConfig.setRequestTimeout(REQ_TIME_OUT);

        ClusterClientConfigManager.applyNewAssignConfig(assignConfig);
        ClusterClientConfigManager.applyNewConfig(clientConfig);
    }
}
