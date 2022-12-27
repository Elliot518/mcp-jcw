package com.mcp.infrastructure.common.domain.dto.auth;

import lombok.Data;

/**
 * @author: KG
 * @description: Single Sign On Dto
 * @date: Created in 1:52 下午 2020/9/21
 * @modified by:
 */
@Data
public class SsoDto {
    protected String accessToken;

    protected String refreshToken;

    protected String clientId;

    protected String loginName;
}
