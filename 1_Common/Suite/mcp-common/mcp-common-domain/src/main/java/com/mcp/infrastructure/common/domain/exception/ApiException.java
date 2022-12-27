package com.mcp.infrastructure.common.domain.exception;

import com.mcp.infrastructure.common.domain.api.CommonResultCode;
import com.mcp.infrastructure.common.domain.api.IErrorCode;
import com.mcp.infrastructure.common.domain.code.BaseCode;
import lombok.Getter;

/**
 * @author: KG
 * @description: 自定义接口异常
 * @date: Created in 5:41 下午 2020/8/5
 * @modified by:
 */
@Getter
public class ApiException extends RuntimeException {
    protected String code;
    protected String msg;

    public ApiException() {
        this(BaseCode.InterfaceError.CODE, BaseCode.InterfaceError.MSG);
    }

    public ApiException(String msg) {
        this(BaseCode.InterfaceError.CODE, msg);
    }

    public ApiException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public ApiException(CommonResultCode commonResultCode) {
        this.code = commonResultCode.getCode();
        this.msg = commonResultCode.getMessage();
    }

    public ApiException(IErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMessage();
    }
}
