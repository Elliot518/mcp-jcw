package com.mcp.infrastructure.common.domain.api;

import lombok.Getter;

/**
 * @author: KG
 * @description: 业务异常类
 * @date: Created in 10:55 上午 2020/11/3
 * @modified by:
 */
@Getter
public class ResultException extends Exception {
    /**
     * 业务异常信息信息
     */
    ResultCode resultCode;

    public ResultException() {
        this(ResultCode.INTERNAL_SERVER_ERROR);
    }

    public ResultException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }
}
