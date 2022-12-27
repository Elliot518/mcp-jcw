package com.mcp.infrastructure.common.domain.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author : KG
 * description: 时间记录切面, 收集接口的运行时间
 * create date: 4:40 PM 2019/11/12
 * modified by:
 */

@Slf4j
public class TimeAspector {
    public static Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取目标Logger
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());

        // 获取目标类名称
        String clazzName = joinPoint.getTarget().getClass().getName();

        // 获取目标类方法名称
        String methodName = joinPoint.getSignature().getName();

        long start = System.currentTimeMillis();
        logger.info( "################## {}: {}: start...", clazzName, methodName);

        // 调用目标方法
        Object result = joinPoint.proceed();

        long time = System.currentTimeMillis() - start;
        logger.info( "    ############## {}: {}: : end... cost time: {} ms", clazzName, methodName, time);

        return result;
    }
}
