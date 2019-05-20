package com.gupaoedu.concurrent.application.optimized;

import com.gupaoedu.concurrent.application.RequestDto;
import com.gupaoedu.concurrent.application.RequestElement;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author laihui
 * @Date 2019/5/20
 * @Desc 批量投保处理，处理合作方批量投保请求
 * @Version 1.0
 **/
public class OptimizedBatchInsure {
    public void doInsure(RequestDto request) {
        long startTime = System.currentTimeMillis();
        System.out.println("===========前端请求数据：" + request);
        int reqNum = request.getElements().size();
        System.out.println("===========共有" + reqNum + "笔业务请求");

        ExecutorService executorService = Executors.newFixedThreadPool(reqNum);
        CountDownLatch latch = new CountDownLatch(reqNum);
        for (int i = 0; i < reqNum; i++) {
            executorService.execute(new InsureThread(request.getElements().get(i), i, latch));
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println("============本次请求处理完毕，总耗时：" + (System.currentTimeMillis() - startTime) + "ms");

    }
}
