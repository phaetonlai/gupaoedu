package com.gupao.sentinel.token.server;

import com.alibaba.csp.sentinel.cluster.server.ClusterTokenServer;
import com.alibaba.csp.sentinel.cluster.server.SentinelDefaultTokenServer;
import com.alibaba.csp.sentinel.cluster.server.config.ClusterServerConfigManager;
import com.alibaba.csp.sentinel.cluster.server.config.ServerTransportConfig;

import java.util.Collections;

/**
 * @Author laihui
 * @Date 2019/8/13
 * @Desc
 * @Version 1.0
 **/
public class ClusterServer {
    public static void main(String[] args) throws Exception {
        ClusterTokenServer tokenServer = new SentinelDefaultTokenServer();
        ClusterServerConfigManager.loadGlobalTransportConfig(
                new ServerTransportConfig().setIdleSeconds(600).setPort(9090));
        ClusterServerConfigManager.loadServerNamespaceSet(Collections.singleton("App-Phae"));
        tokenServer.start();
    }
}
