package com.mcp.infrastructure.common.domain.api;

/**
 * @ author: KG
 * Created by KG on 2020/9/1
 */

public enum ResultCode implements IErrorCode {
    /* 成功: 0 */
    SUCCESS("0", "操作成功"),

    /* 常用错误 */
    INTERNAL_SERVER_ERROR("500", "操作失败"),
    UNAUTHORIZED("401", "暂未登录或token已经过期"),
    FORBIDDEN("403", "没有相关权限"),
    DEFAULT_AUTH("5100", "认证中心回滚"),

    /* 参数错误: 1000 - 1999 */
    PARAM_INVALID("1000", "参数检验失败"),

    /* 用户错误: 2000 - 2999 */
    USER_NOT_LOGIN("2000", "用户未登录");

    private String code;
    private String message;

    private ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
