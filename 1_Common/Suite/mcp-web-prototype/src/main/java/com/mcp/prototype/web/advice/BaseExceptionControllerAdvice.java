package com.mcp.prototype.web.advice;


import com.mcp.infrastructure.common.domain.api.Result;
import com.mcp.infrastructure.common.domain.helper.ExceptionAssistant;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;

/**
 * @author: KG
 * @description: 全局异常处理器
 * @date: Created in 5:56 下午 2020/8/5
 * @modified by: KG 2020/8/11
 */
public class BaseExceptionControllerAdvice {
    public Result<String> constraintExceptionHandler(ConstraintViolationException e) {
        return ExceptionAssistant.captureParamError(e.getMessage());
    }

    public Result<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);

        return ExceptionAssistant.captureParamError(objectError.getDefaultMessage());
    }

    public Result<String> apiExceptionHandler(Exception e) {
        return ExceptionAssistant.captureApiException(e);
    }

}







