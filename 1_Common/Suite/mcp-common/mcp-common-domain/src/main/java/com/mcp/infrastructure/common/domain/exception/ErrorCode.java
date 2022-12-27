package com.mcp.infrastructure.common.domain.exception;

/**
 * @author: KG
 * @description: 错误码接口
 * @date: Created in 9:11 PM 2019/8/30
 * @modified by:
 */

public interface ErrorCode {

    /**
     * 获取错误码
     *
     * @return
     */
    String getCode();

    /**
     * 获取错误信息
     *
     * @return
     */
    String getDescription();
}

