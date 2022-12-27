package com.mcp.infrastructure.common.domain.response;

import com.mcp.infrastructure.common.domain.dto.TokenData;

import java.io.Serializable;

/**
 * @author: KG
 * @description:
 * @date: Created in 下午5:34 2018/4/12
 * @modified by:
 */

public class TokenResponse<T> extends BaseResponse<TokenData<T>> implements Serializable {
}
