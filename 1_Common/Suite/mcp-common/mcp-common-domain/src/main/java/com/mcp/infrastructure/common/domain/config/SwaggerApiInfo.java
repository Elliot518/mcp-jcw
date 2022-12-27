package com.mcp.infrastructure.common.domain.config;

import lombok.Data;

/**
 * @author: KG
 * @description:
 * @date: Created in 10:05 下午 2020/8/10
 * @modified by:
 */
@Data
public class SwaggerApiInfo {
    private String basePackage;

    private String title;

    private String description;

    private String author;

    private String serviceUrl;

    private String email;

    private String version;

    private String tokenHead;
}


