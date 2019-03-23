package com.gupao.gp17190.pattern.proxy.refactor.optimized;

import com.gupao.gp17190.pattern.proxy.refactor.RequestDto;
import com.gupao.gp17190.pattern.proxy.refactor.ResultInfo;

/**
 * @author laihui
 * @date 2019-3-23
 * @version 1.0
 * @description 承保处理规范
 */
public interface IAcceptService {
    ResultInfo doAccept(RequestDto dto);
}
