package com.gupao.sentinel.token.server;

import com.alibaba.csp.sentinel.cluster.flow.rule.ClusterFlowRuleManager;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
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
    private final String ADDRESS = "localhost,localhost,localhost";
    private final String GROUP_ID = "SENTINEL_GROUP";
    private final String FLOW_SUFFIX = "-flow-rules";

    public void init() throws Exception {
        ClusterFlowRuleManager.setPropertySupplier(namespace -> {
            ReadableDataSource<String, List<FlowRule>> dataSource =
                    new NacosDataSource<>(ADDRESS, GROUP_ID, namespace + FLOW_SUFFIX,
                            source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>(){}));

            return dataSource.getProperty();
        });
    }
}
