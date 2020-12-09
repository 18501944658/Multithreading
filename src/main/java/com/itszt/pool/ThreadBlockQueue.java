package com.itszt.pool;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 阻塞队列
 * 对于超过核心线程数的任务,入队列
 */
@Slf4j(topic = "ThreadBlockQueue")
@Getter
public class ThreadBlockQueue {

    Lock lock = new ReentrantLock();
    /**
     * 队列中满后进入等待状态
     **/
    Condition busyawait = lock.newCondition();
    /**
     * 队列为空,需等待,新任务进入,在唤醒拿任务
     **/
    Condition emptyawait = lock.newCondition();


    /**
     * 阻塞队列大小
     **/
    private Integer queueSize;

   private Deque<RunTask> deque = new ArrayDeque();

    public ThreadBlockQueue(Integer queueSize) {
        this.queueSize = queueSize;
    }




    /**
     * 入队列
     **/
    public void put(RunTask runTask) {
        log.debug("当前任务-{}-尝试进入阻塞队列等待---",runTask.getTaskname());
        lock.lock();
        try {
            /**尝试入队**/
            while (deque.size() == queueSize) {
                log.debug("阻塞队列已满,任务{}进入条件busy锁队列等待！！！", runTask.getTaskname());
                /**队列已满,线程等待睡眠**/
                try {
                    busyawait.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            /**入队列**/
            deque.add(runTask);
            /**唤醒正在等待拿空队列中任务的线程,新入一个任务,则empty条件锁中线程可以拿任务**/
            emptyawait.signal();
            log.debug("任务{},进入阻塞队列成功！！！", runTask.getTaskname());
        } finally {
            lock.unlock();
        }
    }

    /**
     * 出队一个
     */
    public RunTask poll() {
        log.debug("当前核心线程池任务执行完,需去阻塞队列获取新任务！！！");
        lock.lock();
        /**判断队列是否为空**/
        try {
            while (deque.isEmpty()) {
                log.debug("阻塞队列中任务为空,进入条件empty锁队列等待！！！");
                /**空,线程入队列等待唤醒**/
                try {
                    emptyawait.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (deque.isEmpty()) {
                    return null;
                }
            }
            RunTask runTask = deque.removeFirst();
            log.debug("阻塞队列取出任务-{}-给线程池去执行！！！,还有{}个任务待执行", runTask.getTaskname(),deque.size());
            /**阻塞队列中新出队列执行任务,队列中未满,新任务可以入队列,唤醒busy锁等待的任务入队列**/
            busyawait.signal();
            return runTask;
        } finally {
            lock.unlock();
        }
    }
}
