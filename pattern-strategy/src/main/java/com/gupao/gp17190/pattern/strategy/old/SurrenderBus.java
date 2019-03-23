package com.gupao.gp17190.pattern.strategy.old;

import com.gupao.gp17190.pattern.strategy.*;

/**
 * 保险业务，退保主入口
 *
 */
public class SurrenderBus {

    /**
     * 退保主方法
     */
    public ResultInfo batchSurrender(ContractEO contractEO) {
        // 1. 根据保单信息，确定保单所属核心（不同保单可能来自不同核心）
        String coreCode = contractEO.getCoreCode();

        // 2. 根据保单所属核心，调用对应退保接口
        ResultInfo resultInfo = null;
        if (Const.LIFE_CORE_CODE.equals(coreCode)) {
            resultInfo = new LifeCoreSurrenderService().lifeSurrender(contractEO);
        } else if (Const.ACCIDENT_CORE_CODE.equals(coreCode)) {
            resultInfo = new AccidentCoreSurrenderService().accidentSurrender(contractEO);
        } else if (Const.HEALTH_CORE_CODE.equals(coreCode)) {
            resultInfo = new HealthCoreSurrenderService().healthSurrender(contractEO);
        } else {
            resultInfo = new PropertyCoreSurrenderService().propertySurrender(contractEO);
        }

        // 3. 返回结果信息
        return resultInfo;
    }



}
