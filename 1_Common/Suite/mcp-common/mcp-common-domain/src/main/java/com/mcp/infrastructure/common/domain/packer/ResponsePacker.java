package com.mcp.infrastructure.common.domain.packer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcp.infrastructure.common.domain.exception.ApiException;
import com.mcp.infrastructure.common.domain.helper.ResponseHelper;

/**
 * @author: KG
 * @description: 返回结果包装器
 * @date: Created in 11:17 上午 2020/8/20
 * @modified by:
 */

public class ResponsePacker {
    public static Object wrapData(Object data, boolean isStringType) throws ApiException {
        // String类型不能直接包装，所以要进行些特别的处理
        if (isStringType) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // 将数据包装在BaseResponse里后，再转换为json字符串响应给前端
                return objectMapper.writeValueAsString(ResponseHelper.success(data));
            } catch (JsonProcessingException e) {
                throw new ApiException("返回String类型错误");
            }
        }

        // 将原本的数据包装在BaseResponse里
        return ResponseHelper.success(data);
    }
}


