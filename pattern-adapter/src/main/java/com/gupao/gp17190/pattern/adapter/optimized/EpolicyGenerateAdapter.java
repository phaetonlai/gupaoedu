package com.gupao.gp17190.pattern.adapter.optimized;

import com.gupao.gp17190.pattern.adapter.EpolicyRequestDto;
import com.gupao.gp17190.pattern.adapter.ResultInfo;
import com.gupao.gp17190.pattern.adapter.old.IEpolicyGenerateService;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/3/27
 * @description
 **/
public class EpolicyGenerateAdapter implements IEpolicyGenerateService {
    public ResultInfo epolicyGen(EpolicyRequestDto dto) {
        Class<? extends IEpolicyGenerateService> suppportClass = findSupport(dto.getEpolicyType());
        try {
            return suppportClass.newInstance().epolicyGen(dto);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Class findSupport(String epolicyType) {
        return EpolicyRequestDto.EpolicyType.NEW.value().equals(epolicyType)
                ? EpolicyGenerateNewServiceImpl.class
                : EpolicyGenerateOldServiceImpl.class;
    }
}
