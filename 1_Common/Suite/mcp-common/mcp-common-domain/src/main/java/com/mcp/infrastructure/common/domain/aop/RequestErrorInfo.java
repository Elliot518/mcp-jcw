package com.mcp.infrastructure.common.domain.aop;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: KG
 * @description:
 * @date: Created in 8:58 下午 2021/1/26
 * @modified by:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RequestErrorInfo extends BaseRequestInfo {
    private RuntimeException exception;
}

