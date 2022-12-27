package com.mcp.infrastructure.common.core.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: KG
 * @description: 自定义线程池[定制属于自己的阻塞线程池]
 * @date: Created in 14:44 2022/4/24
 * @modified by:
 */
@Slf4j
public class CustomThreadPoolExecutor {
    private ThreadPoolExecutor pool = null;

    /**
     * 线程池初始化方法
     * corePoolSize 核心线程池大小
     * maximumPoolSize 最大线程池大小
     * keepAliveTime 线程池中超过corePoolSize数目的空闲线程最大存活时间----30+单位TimeUnit
     * TimeUnit keepAliveTime时间单位----TimeUnit.MINUTES
     * workQueue 阻塞队列----new ArrayBlockingQueue<Runnable>(num) num容量的阻塞队列
     * threadFactory 新建线程工厂----new CustomThreadFactory()====定制的线程工厂
     * rejectedExecutionHandler 当提交任务数超过maximumPoolSize+workQueue之和时,
     * 即当提交第maximumPoolSize+num+1个任务时, 任务会交给RejectedExecutionHandler来处理
     * @author KG
     * @date 2022/4/24 16:41
     * @param setting 线程池设置
     */
    public void init(PoolExecutorSetting setting) {
        pool = new ThreadPoolExecutor(
                setting.getCorePoolSize(),
                setting.getMaximumPoolSize(),
                setting.getKeepAliveTime(),
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(setting.getBlockingQueueCapacity()),
                new CustomThreadFactory(),
                new CustomRejectedExecutionHandler());
    }

    /**
     * 销毁线程池
     * @author KG
     * @date 2022/4/24 16:41
     */
    public void destory() {
        if (pool != null) {
            pool.shutdownNow();
        }
    }

    /**
     * 返回自定义线程池
     * @author KG
     * @date 2022/4/24 16:43
     * @return java.util.concurrent.ExecutorService
     */
    public ExecutorService getCustomThreadPoolExecutor() {
        return this.pool;
    }

    /**
     * 定制的线程工厂
     * @author KG
     */
    private static class CustomThreadFactory implements ThreadFactory {
        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String threadName = CustomThreadPoolExecutor.class.getSimpleName() + count.addAndGet(1);
            log.info("创建自定义线程: {}", threadName);
            t.setName(threadName);
            return t;
        }
    }

    /**
     * 自定义拒绝策略
     * @author KG
     * @date 2022/4/24 16:49
     */
    private static class CustomRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                // 核心改造点，由blockingqueue的offer改成put阻塞方法
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                log.error("#####线程池自定义拒绝策略异常: ", e);
            }
        }
    }
}
