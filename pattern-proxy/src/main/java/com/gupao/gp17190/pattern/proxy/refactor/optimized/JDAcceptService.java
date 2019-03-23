package com.gupao.gp17190.pattern.proxy.refactor.optimized;

import com.gupao.gp17190.pattern.proxy.refactor.RequestDto;
import com.gupao.gp17190.pattern.proxy.refactor.ResultInfo;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/3/23
 * @description
 **/
public class JDAcceptService implements IAcceptService {
    private BaseAcceptService baseService;

    public JDAcceptService() {
        baseService = new BaseAcceptService();
    }

    public ResultInfo doAccept(RequestDto dto) {
        System.out.println("优化后的承保请求处理；合作方：京东，开始处理承保请求。。。");
        System.out.println("优化后的承保请求处理；对请求数据进行转换。。。");
        System.out.println("优化后的承保请求处理；京东请求数据转换完毕，开始处理。。。");
        return baseService.doAccept(dto);
    }
}
