package com.mcp.components.java.concurrency.thread.domain;

import com.mcp.components.java.concurrency.thread.partition.GenericPartitionListThread;
import com.mcp.test.domain.person.Employee;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author: KG
 * @description:
 * @date: Created in 11:03 下午 2020/7/17
 * @modified by:
 */
@Slf4j
public class EmployeePartitionListThread extends GenericPartitionListThread<Employee> {
    private BizService bizService;

    public EmployeePartitionListThread(int threadNum, List<Employee> dataList) {

        super(threadNum, dataList);
        bizService = new BizService();
    }

    @Override
    public void callback(List<Employee> partitionList) {
        bizService.displayEmployees(partitionList);
    }
}
