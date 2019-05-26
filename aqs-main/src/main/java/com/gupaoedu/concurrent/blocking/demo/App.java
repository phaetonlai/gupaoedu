package com.gupaoedu.concurrent.blocking.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/5/26
 * @description
 **/
public class App {
    public static void main(String[] args) {
        ArrayBlockingQueue queue = new ArrayBlockingQueue(16);
        List<String> data = genData();
        FileReadThread fileReadThread = new FileReadThread(queue, data);
        fileReadThread.start();
        for (int i = 0; i < data.size(); i++) {
            RequestSendThread requestSendThread = new RequestSendThread(queue);
            requestSendThread.start();
        }
    }

    private static List<String> genData() {
        List<String> list = new ArrayList<String>();
        Random random = new Random();
        int num = random.nextInt(25) + 1;
        for (int i = 0; i < num; i++) {
            list.add("000000" + (i + 1));
        }
        return list;
    }
}
