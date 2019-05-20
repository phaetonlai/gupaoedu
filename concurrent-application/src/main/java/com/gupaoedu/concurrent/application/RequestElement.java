package com.gupaoedu.concurrent.application;

import java.util.Map;

/**
 * @Author laihui
 * @Date 2019/5/20
 * @Desc
 * @Version 1.0
 **/
public class RequestElement {
    private String reqId;// 请求id
    private Map<String, Object> bizData;// 请求业务数据

    public RequestElement(String reqId, Map<String, Object> bizData) {
        this.reqId = reqId;
        this.bizData = bizData;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public Map<String, Object> getBizData() {
        return bizData;
    }

    public void setBizData(Map<String, Object> bizData) {
        this.bizData = bizData;
    }

    @Override
    public String toString() {
        return "{reqId:" + reqId + ", bizData: " + bizData + "}";
    }
}
