package com.gupao.gp17190.pattern.adapter;

import com.gupao.gp17190.pattern.adapter.old.EpolicyGenerateServiceImpl;
import com.gupao.gp17190.pattern.adapter.old.IEpolicyGenerateService;
import com.gupao.gp17190.pattern.adapter.optimized.EpolicyGenerateAdapter;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/3/27
 * @description
 **/
public class AdapterTest {
    public static void main(String[] args) {
        EpolicyRequestDto dto = new EpolicyRequestDto();
        dto.setPolicyNo("ASHZ2019032718090001");
        dto.setEpolicyType(EpolicyRequestDto.EpolicyType.NEW.value());

        IEpolicyGenerateService service = new EpolicyGenerateServiceImpl();
        System.out.println(service.epolicyGen(dto));

        System.out.println("=================");

        service = new EpolicyGenerateAdapter();
        System.out.println(service.epolicyGen(dto));
    }
}
