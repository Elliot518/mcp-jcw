package com.mcp.infrastructure.common.domain.api;

/**
 * 枚举了一些常用API操作码
 * Created by KG on 2020/9/1
 */

public enum CommonResultCode implements IErrorCode {
    SUCCESS("0", "操作成功"),
    FAILED("500", "操作失败"),
    VALIDATE_FAILED("1001", "参数检验失败"),
    UNAUTHORIZED("401", "暂未登录或token已经过期"),
    FORBIDDEN("403", "没有相关权限"),
    DEFAULT_AUTH("5100", "认证中心回滚"),

    /**
     * PDA Code and Message
     */


    RT_SUCCESS("0", "请求响应成功"),
    RT_FAILURE("1", "请求响应失败"),
    ADD_ERROR("2","新增失败"),
    UPDATE_ERROR("3","新增失败"),
    DELETE_ERROR("4", "删除失败"),
    DUPLICATE_NUMBER("5","编号重复");

    private String code;
    private String message;

    private CommonResultCode(String code, String message) {
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
