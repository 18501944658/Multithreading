package com.itszt.interrupt;

public class TestInterrupt {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            boolean interrupted = Thread.currentThread().isInterrupted();

            System.out.println("interrupted = " + interrupted);
            System.out.println("我是线程t1正在执行");
        });
        t1.start();
        t1.interrupt();
    }
}
