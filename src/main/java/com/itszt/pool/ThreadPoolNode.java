package com.itszt.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Queue;

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
public class ThreadPoolNode {
    /**核心线程数**/
    private Integer coreSize;

    /**空闲线程数**/
    private Integer MaxSize;

    /**核心线程池**/
    private HashSet<RunThreadNode> coreSet;

    /**阻塞队列**/
    private ThreadBlockQueue blockQueue;





}
