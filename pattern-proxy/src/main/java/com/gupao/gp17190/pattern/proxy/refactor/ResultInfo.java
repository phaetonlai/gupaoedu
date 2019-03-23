package com.gupao.gp17190.pattern.proxy.refactor;

/**
 * @Author laihui
 * @Date 2019/3/21
 * @Desc 处理结果信息对象
 * @Version 1.0
 **/
public class ResultInfo {

    private String msg;// 结果描述信息

    private boolean success;// 处理成功标识

    private Object data;// 处理结果需要传输的数据

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "处理结果标识：【" + this.isSuccess() + "】，处理结果信息：" + this.getMsg();
    }
}
