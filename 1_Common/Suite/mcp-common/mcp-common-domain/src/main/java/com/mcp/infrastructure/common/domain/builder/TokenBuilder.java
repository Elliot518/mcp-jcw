package com.mcp.infrastructure.common.domain.builder;

import com.mcp.infrastructure.common.domain.dto.TokenData;

/**
 * @author: KG
 * @description:
 * @date: Created in 10:47 上午 2020/6/24
 * @modified by:
 */

public class TokenBuilder<T> {
    public static <T> TokenData<T> buildTokenData(String prefix, T data) {
        TokenData<T> tokenData = new TokenData<>();
        tokenData.setPrefix(prefix);
        tokenData.setData(data);

        return tokenData;
    }
}
