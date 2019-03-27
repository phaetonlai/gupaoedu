package com.gupao.gp17190.pattern.adapter.old;

import com.gupao.gp17190.pattern.adapter.EpolicyRequestDto;
import com.gupao.gp17190.pattern.adapter.ResultInfo;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/3/27
 * @description
 **/
public class EpolicyGenerateServiceImpl implements IEpolicyGenerateService {

    // 原有的电子保单生成方法，通过调用电子保单系统的接口进行生成
    // 电子保单系统升级，对电子保单生成接口进行了升级
    // 但是原有的接口要并行使用一段时间，既有的产品需要逐步切换至新接口
    public ResultInfo epolicyGen(EpolicyRequestDto dto) {
        String epolicyType = dto.getEpolicyType();
        ResultInfo resultInfo = new ResultInfo();
        if (EpolicyRequestDto.EpolicyType.NEW.value().equals(epolicyType)) {// 该部分为新增代码
            System.out.println("调用新接口生成电子保单。。。");
            System.out.println("调用新接口生成电子保单完成。。。");
            resultInfo.setSuccess(true);
            resultInfo.setMsg("保单号：【" + dto.getPolicyNo() + "】，处理是否成功：【" + resultInfo.isSuccess() + "】");

            return resultInfo;
        }

        System.out.println("调用老接口生成电子保单。。。");
        System.out.println("调用老接口生成电子保单完成。。。");
        resultInfo.setSuccess(true);
        resultInfo.setMsg("保单号：【" + dto.getPolicyNo() + "】，处理是否成功：【" + resultInfo.isSuccess() + "】");

        return resultInfo;
    }
}
