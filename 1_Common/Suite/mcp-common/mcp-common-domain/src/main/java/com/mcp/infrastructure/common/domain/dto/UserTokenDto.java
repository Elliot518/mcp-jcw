package com.mcp.infrastructure.common.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: KG
 * @description:
 * @date: Created in 3:02 下午 2020/6/23
 * @modified by:
 */

@Data
public class UserTokenDto implements Serializable {
    private String userId;

    private String userName;

    private String mobile;

    private String platformId;

    private Integer systemId;

    private String bizPrefix;
}
