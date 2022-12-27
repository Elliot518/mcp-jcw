package com.mcp.infrastructure.common.domain.aop;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: KG
 * @description:
 * @date: Created in 8:56 下午 2021/1/26
 * @modified by:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApiRequestInfo extends BaseRequestInfo {
    private Object result;

    private Long timeCost;
}

