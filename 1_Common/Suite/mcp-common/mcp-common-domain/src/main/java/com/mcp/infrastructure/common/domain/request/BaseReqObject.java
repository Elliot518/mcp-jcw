package com.mcp.infrastructure.common.domain.request;

import lombok.Data;

/**
 * @author: KG
 * @description:
 * @date: Created in 3:14 下午 2020/6/23
 * @modified by: KG
 */
@Data
public class BaseReqObject {
    protected String platformId;

    protected Integer systemId;
}
