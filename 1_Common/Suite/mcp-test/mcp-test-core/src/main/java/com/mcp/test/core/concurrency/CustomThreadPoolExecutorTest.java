package com.mcp.test.core.concurrency;

import com.mcp.infrastructure.common.core.concurrency.CustomThreadPoolExecutor;
import com.mcp.infrastructure.common.core.concurrency.PoolExecutorSetting;

import java.util.concurrent.ExecutorService;

/**
 * @author: KG
 * @description:
 * @date: Created in 17:18 2022/4/24
 * @modified by:
 */
public class CustomThreadPoolExecutorTest {
    public static void main(String[] args) {
        CustomThreadPoolExecutor exec = new CustomThreadPoolExecutor();
        // 1.初始化
        PoolExecutorSetting setting = PoolExecutorSetting.builder()
                .corePoolSize(10)
                .maximumPoolSize(30)
                .keepAliveTime(30)
                .blockingQueueCapacity(10)
                .build();

        exec.init(setting);

        ExecutorService pool = exec.getCustomThreadPoolExecutor();
        for (int i = 1; i < 100; i++) {
            System.out.println("提交第" + i + "个任务!");
            pool.execute(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("running=====");
            });
        }


        // 2.销毁----此处不能销毁,因为任务没有提交执行完,如果销毁线程池,任务也就无法执行了
        // exec.destory();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
