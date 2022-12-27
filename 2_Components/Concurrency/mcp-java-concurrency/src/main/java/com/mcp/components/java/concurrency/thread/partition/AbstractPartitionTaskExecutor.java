package com.mcp.components.java.concurrency.thread.partition;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: KG
 * @description: 分区任务执行器(抽象类)
 * @date: Created in 2021年09月07日 2:54 PM
 * @modified by:
 */
public abstract class AbstractPartitionTaskExecutor<T> {

    protected abstract GenericPartitionListThread<T> createPartitionThread(int threadNum, List<T> partitionList);

    public void startPartitionThreads(List<T> dataList, Integer threadNum, int threadPoolSize) {
        ExecutorService threadPool = Executors.newFixedThreadPool(threadPoolSize);

        Map<Integer, List<T>> partitionedMapData = GenericListPartitioner.split(dataList, threadNum);
        for (Map.Entry<Integer, List<T>> entry : partitionedMapData.entrySet()) {
            Integer index = entry.getKey();
            List<T> partitionList = entry.getValue();
            GenericPartitionListThread<T> thread = createPartitionThread(threadNum, partitionList);
            threadPool.submit(thread);
        }
    }
}
