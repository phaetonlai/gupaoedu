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
public class EpolicyGenerateNewServiceImpl implements IEpolicyGenerateService {

    // 原有的电子保单生成方法，通过调用电子保单系统的接口进行生成
    // 电子保单系统升级，对电子保单生成接口进行了升级
    // 但是原有的接口要并行使用一段时间，既有的产品需要逐步切换至新接口
    public ResultInfo epolicyGen(EpolicyRequestDto dto) {

        System.out.println("优化后，调用新接口生成电子保单。。。");
        System.out.println("优化后，调用新接口生成电子保单完成。。。");

        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setSuccess(true);
        resultInfo.setMsg("保单号：【" + dto.getPolicyNo() + "】，调用新接口，处理是否成功：【" + resultInfo.isSuccess() + "】");

        return resultInfo;
    }
}
