package com.mcp.infrastructure.common.domain.exception;


import org.apache.commons.lang3.StringUtils;

/**
 * @author: KG
 * @description: 业务错误码
 * @date: Created in 9:13 PM 2019/8/30
 * @modified by:
 */

public enum BaseErrorCodeEnum implements ErrorCode {

    /** 基础异常 */
    UNSPECIFIED("500", "网络异常，请稍后再试"),
    NO_SERVICE("404", "网络异常, 服务器熔断"),
    UNAUTHORIZED("401", "暂未登录或token已经过期"),

    ;

    /** 错误码 */
    private final String code;

    /** 描述 */
    private final String description;

    /**
     * @param code 错误码
     * @param description 描述
     */
    private BaseErrorCodeEnum(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据编码查询枚举。
     *
     * @param code 编码。
     * @return 枚举。
     */
    public static BaseErrorCodeEnum getByCode(String code) {
        for (BaseErrorCodeEnum value : BaseErrorCodeEnum.values()) {
            if (StringUtils.equals(code, value.getCode())) {
                return value;
            }
        }
        return UNSPECIFIED;
    }

    /**
     * 枚举是否包含此code
     * @param code 枚举code
     * @return 结果
     */
    public static Boolean contains(String code){
        for (BaseErrorCodeEnum value : BaseErrorCodeEnum.values()) {
            if (StringUtils.equals(code, value.getCode())) {
                return true;
            }
        }
        return  false;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }
}

