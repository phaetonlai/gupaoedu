package com.gupaoedu.concurrent.blocking.demo;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/5/26
 * @description 发送请求
 **/
public class RequestSendThread extends Thread {
    private ArrayBlockingQueue<RequestDto> requestList;

    public RequestSendThread(ArrayBlockingQueue<RequestDto> requestList) {
        this.requestList = requestList;
    }

    public void sendRequest() throws InterruptedException {
        RequestDto dto = requestList.take();
        System.out.println("==========接收到请求数据：" + dto + "，开始处理。。。。");
        Thread.sleep(500);
        System.out.println("==========请求数据：" + dto + "，处理完毕。。。。");
    }

    @Override
    public void run() {
        try {
            sendRequest();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
