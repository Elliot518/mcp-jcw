package com.mcp.prototype.web.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcp.infrastructure.common.domain.api.ResponseResult;
import com.mcp.infrastructure.common.domain.api.Result;
import com.mcp.infrastructure.common.domain.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.annotation.Annotation;

/**
 * @author: KG
 * @description:
 * @date: Created in 10:48 上午 2020/11/3
 * @modified by:
 */

@Slf4j
@RestControllerAdvice
public class BaseResponseResultAdvice {

    private static final Class<? extends Annotation> ANNOTATION_TYPE = ResponseResult.class;

    /** 判断类或者方法是否使用了 @ResponseResult */
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ANNOTATION_TYPE) || returnType.hasMethodAnnotation(ANNOTATION_TYPE);
    }

    /** 当类或者方法使用了 @ResponseResult 就会调用这个方法 */
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof Result) {
            return body;
        }

        // String类型不能直接包装，所以要进行些特别的处理
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // 将数据包装在BaseResponse里后，再转换为json字符串响应给前端
                return objectMapper.writeValueAsString(Result.success(body));
            } catch (JsonProcessingException e) {
                throw new ApiException("返回String类型错误");
            }
        }

        return Result.success(body);
    }
}




