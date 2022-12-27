package com.mcp.infrastructure.common.domain.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author: KG
 * @description:
 * @date: Created in 4:48 下午 2020/9/4
 * @modified by:
 */
@Data
public class PdaLoginReq extends AuthLoginReq {
    @NotNull(message = "设备码不能为空")
    private String imeiCode;
}
