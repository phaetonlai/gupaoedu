package com.gupaoedu.concurrent.blocking.demo;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/5/26
 * @description 读取文件并解析
 **/
public class FileReadThread extends Thread {

    private ArrayBlockingQueue<RequestDto> requestList;

    private List<String> data;

    public FileReadThread(ArrayBlockingQueue<RequestDto> requestList, List<String> data) {
        this.requestList = requestList;
        this.data = data;
    }

    public void readFile() throws InterruptedException {
        System.out.println("开始解析数据。。。。");
        for (String datum : data) {
            RequestDto dto = new RequestDto(datum, new Date());
            requestList.put(dto);
            System.out.println("数据：" + dto + "，解析完成");
        }
        System.out.println("解析数据完毕。。。。");

    }

    @Override
    public void run() {
        try {
            readFile();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
