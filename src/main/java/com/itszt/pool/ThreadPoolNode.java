package com.itszt.pool;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程池
 *
 * 初始化核心数
 *
 * 空闲数量
 *
 * 空闲队列存活时间
 *
 * 阻塞队列
 */
@Slf4j(topic = "ThreadPoolNode")
@Getter
public class ThreadPoolNode {

    Lock lock= new ReentrantLock();

    /**核心线程数**/
    private Integer coreSize;

    /**空闲线程数**/
    private Integer maxSize;
    /**最大空闲时间**/
    private Integer second;
    /**时间单位**/
    private TimeUnit  timeUnit;
    /**核心线程池**/
    private HashSet<RunThreadNode> coreSet;

    /**阻塞队列**/
    private ThreadBlockQueue blockQueue;


    public ThreadPoolNode(Integer coreSize, Integer maxSize, ThreadBlockQueue blockQueue) {
        this.coreSet= new HashSet<RunThreadNode>();
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.blockQueue = blockQueue;
    }

    public void submitTask(RunTask runTask){
          lock.lock();
        try {
            /**新任务,判断核心线程是否满**/
            if (coreSet.size()<coreSize){
                /**执行任务直接**/
                RunThreadNode runThreadNode= new RunThreadNode(runTask,"t"+(coreSet.size()+1),this);
                log.debug("当前线程池未满,{},线程{}分配了任务{}-----",coreSet.size(),runThreadNode.getName(),runTask.getTaskname());
                /**放入核心线程池中执行**/
                coreSet.add(runThreadNode);
                /****/
                runThreadNode.start();
            }else {
                /**核心线程池已满,判断阻塞队列未满**/
                log.debug("当前线程池中核心线程池已满,{},任务{}需阻塞队列等待",coreSet.size(),runTask.getTaskname());
                /**核心线程已满,入队列**/
                blockQueue.put(runTask);
      }
        } finally {
   lock.unlock();
        }


    }
}
