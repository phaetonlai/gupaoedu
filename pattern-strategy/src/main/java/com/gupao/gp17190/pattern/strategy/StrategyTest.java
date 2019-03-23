package com.gupao.gp17190.pattern.strategy;

import com.gupao.gp17190.pattern.strategy.old.SurrenderBus;
import com.gupao.gp17190.pattern.strategy.optimized.StrategySurrenderBus;

public class StrategyTest {
    public static void main(String[] args) {
        ContractEO contractEO = new ContractEO("AHA12093", Const.LIFE_CORE_CODE, "");
        SurrenderBus bus = new SurrenderBus();
        System.out.println(bus.batchSurrender(contractEO));

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        contractEO = new ContractEO("ASHZ091275" , Const.PROPERTY_CORE_CODE, "");
        StrategySurrenderBus strategySurrenderBus = new StrategySurrenderBus();
        System.out.println(strategySurrenderBus.batchSurrender(contractEO));
    }
}
