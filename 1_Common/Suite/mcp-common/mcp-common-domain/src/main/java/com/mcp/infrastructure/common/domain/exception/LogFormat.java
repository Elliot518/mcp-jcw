package com.mcp.infrastructure.common.domain.exception;

/**
 * @author: KG
 * @description:
 * @date: Created in 2:34 PM 2019/9/4
 * @modified by: KG 2020/8/11
 */

public class LogFormat {
    public static String bizErrorDetail(BizException e) {
        return String.format("**** Error -- Code: %s, Description: %s, Exception Cause: %s", e.getErrorCode().getCode(), e.getErrorCode().getDescription(), e.getMessage());
    }

    public static String apiErrorDetail(ApiException e) {
        return String.format("**** 自定义接口异常 -- Code: %s, Message: %s, Exception Cause: %s", e.getCode(), e.getMsg(), e.getMessage());
    }
}



