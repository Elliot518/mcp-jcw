package com.mcp.infrastructure.common.domain.code.auth;

import com.mcp.infrastructure.common.domain.code.BaseCode;

/**
 * @author: KG
 * @description:
 * @date: Created in 2:41 PM 2019/7/8
 * @modified by:
 */

public interface TokenCode extends BaseCode {
    interface TOKEN_NOT_FOUND {
        String CODE = "40001";
        String MSG = "Token Not Found";
    }
}
