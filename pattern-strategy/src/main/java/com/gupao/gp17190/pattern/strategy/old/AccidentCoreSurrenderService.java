package com.gupao.gp17190.pattern.strategy.old;

import com.gupao.gp17190.pattern.strategy.ContractEO;
import com.gupao.gp17190.pattern.strategy.ResultInfo;

/**
 * 人寿险核心退保处理类
 */
public class AccidentCoreSurrenderService {
    public ResultInfo accidentSurrender(ContractEO contractEO) {
        System.out.println("意外核心退保处理开始。。。");
        System.out.println("退保的保单号：" + contractEO.getContNo());
        System.out.println("意外核心退保处理完毕。。。");

        return new ResultInfo(true, "保单号：" + contractEO.getContNo() + "，退保成功！");
    }
}
