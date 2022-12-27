package com.mcp.infrastructure.common.domain.response;

import java.io.Serializable;

/**
 * @Author: KG
 * @Description:
 * @Date: Created in 上午11:08 2018/3/6
 * @Modified by:
 */

public class BaseResponse<T> implements Serializable {
    protected String code;
    protected String message;
    protected String exception;
    protected T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

