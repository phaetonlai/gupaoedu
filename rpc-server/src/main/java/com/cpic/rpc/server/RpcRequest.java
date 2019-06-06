package com.cpic.rpc.server;

import java.io.Serializable;

/**
 * @Author laihui
 * @Date 2019/6/6
 * @Desc
 * @Version 1.0
 **/
public class RpcRequest implements Serializable {
    private String serviceName;

    private String methodName;

    private String version;

    private Object[] agrs;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Object[] getAgrs() {
        return agrs;
    }

    public void setAgrs(Object[] agrs) {
        this.agrs = agrs;
    }
}
