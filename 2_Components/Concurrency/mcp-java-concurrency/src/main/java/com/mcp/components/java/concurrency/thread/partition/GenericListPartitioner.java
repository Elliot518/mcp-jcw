package com.mcp.components.java.concurrency.thread.partition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: KG
 * @Description: 通用数据集合多线程分割器
 * @Date: Created in 下午3:27 2018/1/24
 * @Modified by:
 */

public class GenericListPartitioner {
    /**
     * Split a data list into partitioned list by partition number
     * @author KG
     * @date 2021/9/7 2:22 PM
     * @param dataList Input data list
     * @param partitionNum Partition number
     * @return java.util.HashMap<java.lang.Integer,java.util.List<T>>
     */
    public static <T> Map<Integer, List<T>> split(List<T> dataList, Integer partitionNum) {
        int sum = dataList.size();
        int avgNum = sum / partitionNum;
        int low = 0;
        int high = avgNum;
        int higher = avgNum;
        HashMap<Integer, List<T>> partitionMap = new HashMap<>(partitionNum);
        int i = 0;

        while (i < partitionNum) {
            low = avgNum * i;
            high = avgNum  * (i + 1) - 1;
            higher = avgNum * (i + 2) - 1;
            if (sum - 1 < higher && sum - 1 > high) { high = sum - 1; }

            List<T> partitionList = new ArrayList<>();
            for (int j = low; j <= high; j++) {
                partitionList.add(dataList.get(j));
            }
            partitionMap.put(i, partitionList);

            i++;
        }

        return partitionMap;
    }
}
