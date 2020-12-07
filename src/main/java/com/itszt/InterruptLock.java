package com.itszt;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "interruptLock")
public class InterruptLock {

    static final ReentrantLock lock = new ReentrantLock();


    public static void main(String[] args) throws InterruptedException {

    }
}
