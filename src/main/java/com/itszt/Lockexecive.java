package com.itszt;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "lock")
public class Lockexecive {

    static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                method1();
            }
        }, "t1");

        t1.start();
    }

    public static void method1() {
        lock.lock();
        try {
            log.debug("enter method1");
            method2();
        } finally {
            lock.unlock();
        }
    }

    //method2 加锁的原因,防止线程直接调用method2
    public static void method2() {
        lock.lock();
        try {
            log.debug("enter method2");
        } finally {
            lock.unlock();
        }


    }
}
