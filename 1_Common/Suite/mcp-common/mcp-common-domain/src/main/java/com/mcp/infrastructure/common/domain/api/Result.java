package com.mcp.infrastructure.common.domain.api;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

/**
 * @author: KG
 * @description: Response return format of controller
 * @date: Created in 10:52 下午 2020/11/2
 * @modified by:
 */
@Data
@Getter
@ToString
public class Result<T> {
    /** 业务错误码 */
    private String code;

    /** 信息描述 */
    private String message;

    /** 返回参数 */
    private T data;

    private Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private Result(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    /** 业务成功返回业务代码和描述信息 */
    public static Result<Void> success() {
        return new Result<Void>(ResultCode.SUCCESS, null);
    }

    /** 业务成功返回业务代码,描述和返回的参数 */
    public static <T> Result<T> success(T data) {
        return new Result<T>(ResultCode.SUCCESS, data);
    }

    /** 业务成功返回业务代码,描述和返回的参数 */
    public static <T> Result<T> success(ResultCode resultCode, T data) {
        if (resultCode == null) {
            return success(data);
        }
        return new Result<T>(resultCode, data);
    }

    /** 业务异常返回业务代码和描述信息 */
    public static <T> Result<T> failure() {
        return new Result<T>(ResultCode.INTERNAL_SERVER_ERROR, null);
    }

    /** 业务异常返回业务代码,描述和返回的参数 */
    public static <T> Result<T> failure(ResultCode resultCode) {
        return failure(resultCode, null);
    }

    /** 业务异常返回业务代码,描述和返回的参数 */
    public static <T> Result<T> failure(ResultCode resultCode, T data) {
        if (resultCode == null) {
            return new Result<T>(ResultCode.INTERNAL_SERVER_ERROR, null);
        }
        return new Result<T>(resultCode, data);
    }

    public static<T> Result<T> failure(String code, String message, T exception) {
        Result<T> result = new Result<>(code, message);

        result.setData(exception);

        return result;
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> Result<T> validateFailed() {
        return failure(ResultCode.PARAM_INVALID);
    }


    /**
     * 未登录返回结果
     */
    public static <T> Result<T> unauthorized() {
        return failure(ResultCode.UNAUTHORIZED);
    }

    /**
     * 未授权返回结果
     */
    public static <T> Result<T> forbidden(T data) {
        return failure(ResultCode.FORBIDDEN);
    }
}

