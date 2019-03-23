package com.gupao.gp17190.pattern.strategy;

/**
 * 保单信息类
 */
public class ContractEO {

    private String contNo;// 保单号

    private String coreCode;// 保单所属核心系统编码

    private String status;// 保单状态

    public ContractEO(String contNo, String coreCode, String status) {
        this.contNo = contNo;
        this.coreCode = coreCode;
        this.status = status;
    }

    public String getContNo() {
        return contNo;
    }

    public void setContNo(String contNo) {
        this.contNo = contNo;
    }

    public String getCoreCode() {
        return coreCode;
    }

    public void setCoreCode(String coreCode) {
        this.coreCode = coreCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
