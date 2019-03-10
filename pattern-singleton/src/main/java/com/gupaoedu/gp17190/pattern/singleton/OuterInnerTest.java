package com.gupaoedu.gp17190.pattern.singleton;

public class OuterInnerTest {
    public static void main(String[] args) {

    }
    public class Inner {

    }

    public static class StaticInner {

    }

    public void test() {
        final int a = 10;
        Thread t = new Thread() {
            @Override
            public void run() {
                System.out.println(a);
            }
        };
        t.start();
    }
}
