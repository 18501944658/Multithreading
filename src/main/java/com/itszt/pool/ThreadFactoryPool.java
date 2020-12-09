package com.itszt.pool;

import java.util.concurrent.ThreadFactory;

public class ThreadFactoryPool implements ThreadFactory {
    private String name;

    public ThreadFactoryPool(String name) {
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r,name);
    }
}
