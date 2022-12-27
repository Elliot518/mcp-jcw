package com.mcp.infrastructure.common.domain.api;

import com.mcp.infrastructure.common.domain.code.BaseCode;
import lombok.Getter;

/**
 * @author: KG
 * @description: 自定义统一响应体
 * @date: Created in 5:46 下午 2020/8/5
 * @modified by: KG 2020/8/11
 */

@Getter
public class ResultVo<T> {
    /**
     * 状态码
     */
    private String code;

    /**
     * 响应信息，用来说明响应情况
     */
    private String message;

    /**
     * 响应的具体数据
     */
    private T data;

    public ResultVo(T data) {
        this(BaseCode.Success.CODE, BaseCode.Success.MSG, data);
    }

    public ResultVo(String code, String msg, T data) {
        this.code = code;
        this.message = msg;
        this.data = data;
    }
}

