package com.gupao.gp17190.pattern.adapter;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/3/27
 * @description
 **/
public class EpolicyRequestDto {
    // 保单号
    private String policyNo;

    // 保单类型标识，根据该字段确定调用电子保单生成接口的接口版本
    private String epolicyType;

    // 详细请求数据
    private Object data;

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getEpolicyType() {
        return epolicyType;
    }

    public void setEpolicyType(String epolicyType) {
        this.epolicyType = epolicyType;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static enum EpolicyType {
        OLD("0"),NEW("1");

        private String typeCode;

        private EpolicyType(String typeCode) {
            this.typeCode = typeCode;
        }

        public String value() {
            return this.typeCode;
        }
    }
}
