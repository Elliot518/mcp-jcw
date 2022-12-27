package com.mcp.infrastructure.common.util.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author : KG
 * description:
 * create date: 9:58 PM 2019/12/5
 * modified by:
 */

public class FastJsonUtils {
    public static <T> String toPrettyJson(T obj) {
        String jsonString = JSON.toJSONString(obj);
        JSONObject object = JSONObject.parseObject(jsonString);
        return JSON.toJSONString(object, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat);
    }
}
