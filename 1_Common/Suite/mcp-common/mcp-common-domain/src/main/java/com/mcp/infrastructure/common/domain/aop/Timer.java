package com.mcp.infrastructure.common.domain.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : KG
 * description: 时间记录annotation
 *              标注需要记录时间消耗的方法
 * create date: 4:38 PM 2019/11/12
 * modified by:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Timer {
}

