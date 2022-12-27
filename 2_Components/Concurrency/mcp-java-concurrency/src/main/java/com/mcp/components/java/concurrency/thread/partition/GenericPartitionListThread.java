package com.mcp.components.java.concurrency.thread.partition;

import java.util.List;

/**
 * @Author: KG
 * @Description:
 * @Date: Created in 下午1:41 2018/1/24
 * @Modified by:
 */

public abstract class GenericPartitionListThread<T> extends Thread {
    private static Integer threadCount = 0;

    private int threadNum;
    private List<T> partitionList;

    public GenericPartitionListThread(int threadNum, List<T> partitionList) {
        this.threadNum = threadNum;
        this.partitionList = partitionList;
    }

    /**
     * Central method of the class
     */
    @Override
    public void run() {
        // business callback
        callback(partitionList);
    }

    /**
     * abstract business callback method
     */
    public abstract void callback(List<T> partitionList);
}




