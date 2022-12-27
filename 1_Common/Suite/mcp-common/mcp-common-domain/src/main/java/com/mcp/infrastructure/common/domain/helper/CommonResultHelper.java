package com.mcp.infrastructure.common.domain.helper;

import com.mcp.infrastructure.common.domain.api.CommonResult;
import com.mcp.infrastructure.common.domain.api.CommonResultCode;

import java.util.HashMap;

/**
 * @author: KG
 * @description:
 * @date: Created in 3:45 下午 2020/9/8
 * @modified by:
 */

public class CommonResultHelper {
    public static CommonResult<HashMap<String, String>> getDefaultResult() {
        return new CommonResult<>(CommonResultCode.DEFAULT_AUTH.getCode(), CommonResultCode.DEFAULT_AUTH.getMessage());
    }
}

