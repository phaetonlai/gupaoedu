package com.gupao.gp17190.pattern.proxy.refactor.optimized;

import com.gupao.gp17190.pattern.proxy.refactor.RequestDto;
import com.gupao.gp17190.pattern.proxy.refactor.ResultInfo;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/3/23
 * @description
 **/
public class BaseAcceptService implements IAcceptService {

    public ResultInfo doAccept(RequestDto dto) {
        System.out.println("优化后的承保处理，请求ID：" + dto.getReqId() + "，承保请求处理完毕！");
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setSuccess(true);
        resultInfo.setMsg("承保请求ID：【" + dto.getReqId() + "】，处理是否成功：【" + resultInfo.isSuccess() + "】");

        return resultInfo;
    }
}
