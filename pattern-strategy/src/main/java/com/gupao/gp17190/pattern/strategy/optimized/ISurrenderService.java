package com.gupao.gp17190.pattern.strategy.optimized;

import com.gupao.gp17190.pattern.strategy.ContractEO;
import com.gupao.gp17190.pattern.strategy.ResultInfo;

public interface ISurrenderService {
    ResultInfo doSurrender(ContractEO contractEO);
}
