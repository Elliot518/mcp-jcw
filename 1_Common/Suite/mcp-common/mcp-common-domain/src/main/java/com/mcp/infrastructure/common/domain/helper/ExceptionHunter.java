package com.mcp.infrastructure.common.domain.helper;

import com.mcp.infrastructure.common.domain.code.BaseCode;
import com.mcp.infrastructure.common.domain.exception.ApiException;
import com.mcp.infrastructure.common.domain.exception.LogFormat;
import com.mcp.infrastructure.common.domain.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: KG
 * @description: 全局异常猎人
 * @date: Created in 1:39 下午 2020/8/11
 * @modified by:
 */
@Slf4j
public class ExceptionHunter {
    public static BaseResponse<String> captureApiException(Exception e) {
        if (e instanceof ApiException) {
            ApiException ex = (ApiException) e;
            String exceptInfo = LogFormat.apiErrorDetail(ex);
            log.error(exceptInfo);

            return ResponseHelper.error(ex.getCode(), ex.getMsg(), ex.getMessage());
        }

//        exceptInfo = String.format("********* Code: %s, Message: %s", BaseCode.UnknownException.CODE, e.getMessage());
//        log.error(exceptInfo);
        log.error("********* 全局异常信息捕获: ", e);

        return ResponseHelper.error(BaseCode.InterfaceError.CODE, BaseCode.InterfaceError.MSG, e.getMessage());
    }

    public static BaseResponse<String> captureParamError(String cause) {
        return ResponseHelper.error(BaseCode.ParamValidationError.CODE, BaseCode.ParamValidationError.MSG, cause);
    }
}
