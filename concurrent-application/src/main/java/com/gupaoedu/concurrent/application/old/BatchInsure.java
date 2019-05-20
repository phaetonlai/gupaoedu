package com.gupaoedu.concurrent.application.old;

import com.gupaoedu.concurrent.application.RequestDto;
import com.gupaoedu.concurrent.application.RequestElement;

/**
 * @Author laihui
 * @Date 2019/5/20
 * @Desc 批量投保处理，处理合作方批量投保请求
 * @Version 1.0
 **/
public class BatchInsure {
    public void doInsure(RequestDto request) {
        long startTime = System.currentTimeMillis();
        System.out.println("前端请求数据：" + request);
        int reqNum = request.getElements().size();
        System.out.println("共有" + reqNum + "笔业务请求");
        for (int i = 0; i < reqNum; i++) {
            RequestElement element = request.getElements().get(i);
            System.out.println("开始处理第" + i + "笔请求数据，请求id：" + element.getReqId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第" + i + "笔请求数据处理完毕！");
        }
        System.out.println("本次请求处理完毕，总耗时：" + (System.currentTimeMillis() - startTime) + "ms");

    }
}
