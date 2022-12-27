package com.mcp.infrastructure.common.domain.helper;

import com.mcp.infrastructure.common.domain.api.CommonResultCode;
import com.mcp.infrastructure.common.domain.code.BaseCode;
import com.mcp.infrastructure.common.domain.exception.ErrorCode;
import com.mcp.infrastructure.common.domain.response.BaseResponse;

/**
 * @author: KG
 * @description:
 * @date: Created in 2:48 PM 2019/7/8
 * @modified by: KG 2020/08/03
 */

public class ResponseHelper {
    /**
     * success by reference
     * @param response
     * @param data
     * @param <T>
     */
    public static<T> void success(BaseResponse<T> response, T data) {
        response.setCode(BaseCode.Success.CODE);
        response.setMessage(BaseCode.Success.MSG);
        response.setData(data);
    }

    /**
     * success with return response
     * @param data
     * @param <T>
     * @return BaseResponse<T>
     */
    public static<T> BaseResponse<T> success(T data) {
        BaseResponse<T> response = new BaseResponse<>();

        response.setCode(BaseCode.Success.CODE);
        response.setMessage(BaseCode.Success.MSG);
        response.setData(data);

        return response;
    }

    /**
     * fail by reference
     * @param response
     * @param code
     * @param message
     * @param <T>
     */
    public static<T> void fail(BaseResponse<T> response, String code, String message) {
        response.setCode(code);
        response.setMessage(message);
    }

    /**
     * error with custom exception
     * @param errCode
     * @param <T>
     * @return
     */
    public static<T> BaseResponse<T> error(ErrorCode errCode) {
        BaseResponse<T> response = new BaseResponse<>();

        response.setCode(errCode.getCode());
        response.setMessage(errCode.getDescription());

        return response;
    }

    public static<T> void failWithException(BaseResponse<T> response, String code, String message, String cause) {
        response.setCode(code);
        response.setMessage(message);
        response.setException(cause);
    }

    public static<T> void except(BaseResponse<T> response, String cause) {
        response.setCode(BaseCode.UnknownException.CODE);
        response.setMessage(BaseCode.UnknownException.MSG);
        response.setException(cause);
    }

    /**
     *
     * @param code
     * @param message
     * @param exception
     * @param <T>
     * @return
     */
    public static<T> BaseResponse<T> error(String code, String message, String exception) {
        BaseResponse<T> response = new BaseResponse<>();

        response.setCode(code);
        response.setMessage(message);
        response.setException(exception);

        return response;
    }

    public static <T> BaseResponse<T> failed(String message) {
        BaseResponse<T> response = new BaseResponse<>();

        response.setCode(CommonResultCode.FAILED.getCode());
        response.setMessage(message);

        return response;
    }
}
