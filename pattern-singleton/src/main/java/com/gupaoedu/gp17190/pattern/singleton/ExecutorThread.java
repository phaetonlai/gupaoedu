package com.gupaoedu.gp17190.pattern.singleton;

public class ExecutorThread implements Runnable {
    public void run() {
        Pojo pojo = Pojo.getInstance();
    }
}
