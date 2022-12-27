package com.mcp.components.java.concurrency.thread.domain;

import com.mcp.components.java.concurrency.thread.partition.AbstractPartitionTaskExecutor;
import com.mcp.components.java.concurrency.thread.partition.GenericPartitionListThread;
import com.mcp.test.domain.person.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: KG
 * @description:
 * @date: Created in 2021年09月07日 3:23 PM
 * @modified by:
 */
public class EmployeesPartitionTaskExecutor extends AbstractPartitionTaskExecutor<Employee> {

    private static List<Employee> initEmployees(int num) {
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Employee employee = new Employee("Employee" + i, i % 100);
            employees.add(employee);
        }

        return employees;
    }

    @Override
    protected GenericPartitionListThread<Employee> createPartitionThread(int threadNum, List<Employee> partitionList) {
        return new EmployeePartitionListThread(threadNum, partitionList);
    }

    public static void main(String[] args) {
        List<Employee> employees = initEmployees(1000);

//        BizService bizService = new BizService();
//        bizService.displayEmployees(employees);

        EmployeesPartitionTaskExecutor executor = new EmployeesPartitionTaskExecutor();
        executor.startPartitionThreads(employees, 5, 4);
    }
}
