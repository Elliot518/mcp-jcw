package com.mcp.infrastructure.common.domain.aop;

import lombok.Data;

/**
 * @author: KG
 * @description:
 * @date: Created in 8:53 下午 2021/1/26
 * @modified by:
 */
@Data
public class BaseRequestInfo {
    protected String ip;

    protected String url;

    protected String httpMethod;

    protected String classMethod;

    protected Object requestParams;
}
