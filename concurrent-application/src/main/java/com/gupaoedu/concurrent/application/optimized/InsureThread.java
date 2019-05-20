package com.gupaoedu.concurrent.application.optimized;

import com.gupaoedu.concurrent.application.RequestElement;

import java.util.concurrent.CountDownLatch;

/**
 * @Author laihui
 * @Date 2019/5/20
 * @Desc
 * @Version 1.0
 **/
public class InsureThread extends Thread {
    private RequestElement element;
    private int index;
    private CountDownLatch latch;

    public InsureThread(RequestElement element, int index, CountDownLatch latch) {
        this.element = element;
        this.index = index;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println("========开始处理第" + index + "笔请求数据，请求id：" + element.getReqId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("=========第" + index + "笔请求数据处理完毕！");
        } finally {
            latch.countDown();
        }
    }
}
