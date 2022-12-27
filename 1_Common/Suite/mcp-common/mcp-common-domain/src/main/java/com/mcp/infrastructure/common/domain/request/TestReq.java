package com.mcp.infrastructure.common.domain.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author: KG
 * @description:
 * @date: Created in 4:49 下午 2020/9/4
 * @modified by:
 */
@Data
public class TestReq {
    @NotNull(message = "公司ID不能为空")
    protected String clientId;

    @NotNull(message = "公司密码不能为空")
    protected String clientSecret;

    @NotNull(message = "用户名不能为空")
    protected String username;

    @NotNull(message = "用户密码不能为空")
    protected String password;

    private String sign;
}
