package com.mcp.components.java.concurrency.thread.domain;

import com.mcp.test.domain.person.Employee;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author: KG
 * @description:
 * @date: Created in 2021年09月07日 5:24 PM
 * @modified by:
 */

@Slf4j
public class BizService {
    public void displayEmployees(List<Employee> employees) {
        try {
            for (Employee employee : employees) {
                Thread.sleep(200);
                log.info("Employee Name: {}", employee.getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
