package com.gupao.gp17190.pattern.strategy.optimized;

import com.gupao.gp17190.pattern.strategy.*;
import com.gupao.gp17190.pattern.strategy.old.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 保险业务，退保主入口
 *
 */
public class StrategySurrenderBus {

    private static Map<String, ISurrenderService> service_map = new HashMap<String, ISurrenderService>();

    static {
        service_map.put(Const.ACCIDENT_CORE_CODE, new AccidentCoreSurrenderService());
        service_map.put(Const.HEALTH_CORE_CODE, new HealthCoreSurrenderService());
        service_map.put(Const.LIFE_CORE_CODE, new LifeCoreSurrenderService());
        service_map.put(Const.PROPERTY_CORE_CODE, new PropertyCoreSurrenderService());
    }

    /**
     * 退保主方法
     */
    public ResultInfo batchSurrender(ContractEO contractEO) {
        // 1. 根据保单信息，确定保单所属核心（不同保单可能来自不同核心）
        String coreCode = contractEO.getCoreCode();

        // 2. 根据保单所属核心，调用对应退保接口
        ResultInfo resultInfo = null;
        if (service_map.containsKey(coreCode)) {
            resultInfo = service_map.get(coreCode).doSurrender(contractEO);
        } else {
            resultInfo = new ResultInfo(false, "保单号：" + contractEO.getContNo() + "退保失败，未找到退保处理类！！");
        }

        // 3. 返回结果信息
        return resultInfo;
    }



}
