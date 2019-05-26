package com.gupaoedu.concurrent.aqs;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/5/20
 * @description
 **/
public class SynchronizedDemo implements Runnable{
    int x = 100;

    public synchronized void m1() {
        x = 1000;// 1
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("x=" + x);// 5
    }

    public synchronized void m2() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        x = 2000;// 2,3
    }
    public static void main(String[] args) throws InterruptedException {
        SynchronizedDemo sd = new SynchronizedDemo();
        new TestThread1(sd).start();
        new TestThread2(sd).start();
        sd.m2();
        System.out.println("Main x=" + sd.x);// 4
    }

    public void run() {
        m1();
    }

    private static class TestThread1 extends Thread {
        private SynchronizedDemo demo;

        public TestThread1(SynchronizedDemo demo) {
            this.demo = demo;
        }

        @Override
        public void run() {
            demo.m1();
        }
    }

    private static class TestThread2 extends Thread {
        private SynchronizedDemo demo;

        public TestThread2(SynchronizedDemo demo) {
            this.demo = demo;
        }

        @Override
        public void run() {
            demo.m2();
        }
    }
}
