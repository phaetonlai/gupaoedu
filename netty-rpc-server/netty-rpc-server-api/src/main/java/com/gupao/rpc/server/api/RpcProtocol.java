package com.gupao.rpc.server.api;

import java.io.Serializable;

/**
 * @Author laihui
 * @Date 2019/6/24
 * @Desc
 * @Version 1.0
 **/
public class RpcProtocol implements Serializable {
    private static final long serialVersionUID = 1L;

    private String className;

    private String methodName;

    private Class<?>[] paramTypes;

    private Object[] params;

    private String version;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class<?>[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
