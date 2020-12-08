package com.itszt.pool;

public class TestThreadPool {
    public static void main(String[] args) {
        ThreadPoolNode threadPoolNode= new ThreadPoolNode(2,3,new ThreadBlockQueue(2));
        for (int i = 1; i < 6; i++) {
        threadPoolNode.submitTask(new RunTask(i+""));
        }
    }
}
