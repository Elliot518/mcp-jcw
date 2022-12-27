package com.mcp.infrastructure.common.domain.request;

import lombok.Data;

/**
 * @author: KG
 * @description:
 * @date: Created in 2:45 下午 2020/6/30
 * @modified by: KG
 */
@Data
public class UserNameLoginReq extends BaseReqObject {
    private String userName;

    private String password;
}
