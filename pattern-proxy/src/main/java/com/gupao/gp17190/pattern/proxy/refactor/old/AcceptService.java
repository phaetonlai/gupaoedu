package com.gupao.gp17190.pattern.proxy.refactor.old;

import com.gupao.gp17190.pattern.proxy.refactor.RequestDto;
import com.gupao.gp17190.pattern.proxy.refactor.ResultInfo;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/3/23
 * @description 承保处理类
 **/
public class AcceptService {
    /**
     * 处理承保请求
     * @param dto
     * @return
     */
    public ResultInfo doAccept(RequestDto dto) {
        if (RequestDto.ParternerCode.JD.getCode().equals(dto.getParternerCode())) {
            System.out.println("合作方：京东，开始处理承保请求。。。");
            System.out.println("对请求数据进行转换。。。");
            System.out.println("京东请求数据转换完毕，开始处理。。。");
        }
        System.out.println("请求ID：" + dto.getReqId() + "，承保请求处理完毕！");
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setSuccess(true);
        resultInfo.setMsg("请求ID：【" + dto.getReqId() + "】，处理是否成功：【" + resultInfo.isSuccess() + "】");

        return resultInfo;
    }
}
