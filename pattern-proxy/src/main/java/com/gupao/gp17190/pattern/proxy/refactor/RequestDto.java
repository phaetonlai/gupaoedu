package com.gupao.gp17190.pattern.proxy.refactor;

/**
 * @Author laihui
 * @Date 2019/3/21
 * @Desc 请求数据对象
 * @Version 1.0
 **/
public class RequestDto {
    private String parternerCode;// 合作方代码

    private String reqId;// 请求id

    private String timestamp;// 时间戳

    private Object reqData;

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getParternerCode() {
        return parternerCode;
    }

    public void setParternerCode(String parternerCode) {
        this.parternerCode = parternerCode;
    }

    public Object getReqData() {
        return reqData;
    }

    public void setReqData(Object reqData) {
        this.reqData = reqData;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public static enum ParternerCode {
        TAOBAO("1"),// 淘宝
        MAYI("2"),// 蚂蚁
        JD("3");// 京东

        private String code;

        public String getCode() {
            return code;
        }

        private ParternerCode(String code) {
            this.code = code;
        }
    }
}
