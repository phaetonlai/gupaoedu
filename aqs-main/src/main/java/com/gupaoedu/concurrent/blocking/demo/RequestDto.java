package com.gupaoedu.concurrent.blocking.demo;

import java.util.Date;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/5/26
 * @description
 **/
public class RequestDto {
    private String reqId;

    private Date reqTime;

    public RequestDto() {
    }

    public RequestDto(String reqId, Date reqTime) {
        this.reqId = reqId;
        this.reqTime = reqTime;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public Date getReqTime() {
        return reqTime;
    }

    public void setReqTime(Date reqTime) {
        this.reqTime = reqTime;
    }

    @Override
    public String toString() {
        return "{reqId:" + this.reqId + ", reqTime:" + this.reqTime + "}";
    }
}
