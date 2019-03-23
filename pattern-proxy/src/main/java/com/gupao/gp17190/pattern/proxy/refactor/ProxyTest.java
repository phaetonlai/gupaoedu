package com.gupao.gp17190.pattern.proxy.refactor;

import com.gupao.gp17190.pattern.proxy.refactor.old.AcceptService;
import com.gupao.gp17190.pattern.proxy.refactor.optimized.JDAcceptService;

/**
 * @Author laihui
 * @Date 2019/3/21
 * @Desc
 * @Version 1.0
 **/
public class ProxyTest {
    public static void main(String[] args) {
        RequestDto dto = new RequestDto();
        dto.setParternerCode(RequestDto.ParternerCode.JD.getCode());
        dto.setReqId("2019032313510001");
        System.out.println(new AcceptService().doAccept(dto));
        System.out.println("======================================");

        System.out.println(new JDAcceptService().doAccept(dto));
    }
}
