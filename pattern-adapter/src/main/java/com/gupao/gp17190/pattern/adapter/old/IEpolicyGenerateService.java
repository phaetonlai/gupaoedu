package com.gupao.gp17190.pattern.adapter.old;

import com.gupao.gp17190.pattern.adapter.EpolicyRequestDto;
import com.gupao.gp17190.pattern.adapter.ResultInfo;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/3/27
 * @description 电子保单生成
 **/
public interface IEpolicyGenerateService {
    ResultInfo epolicyGen(EpolicyRequestDto dto);
}
