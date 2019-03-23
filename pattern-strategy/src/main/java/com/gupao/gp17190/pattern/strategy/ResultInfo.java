package com.gupao.gp17190.pattern.strategy;

/**
 * 结果信息对象
 */
public class ResultInfo {

    private boolean success;// 是否成功

    private String msg;// 处理结果信息

    public ResultInfo(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "退保成功标识：【" + this.success + "】，退保结果信息：【" + this.msg + "】";
    }
}
