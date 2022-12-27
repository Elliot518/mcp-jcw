package com.mcp.infrastructure.common.util.meta;

import lombok.Data;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author: KG
 * @description:
 * @date: Created in 5:50 下午 2020/9/2
 * @modified by:
 */
@Data
public class DefaultTargetType<T> {

    private Type type;
    private Class<T> classType;

    @SuppressWarnings("unchecked")
    public DefaultTargetType() {
        Type superClass = getClass().getGenericSuperclass();
        this.type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
        if (this.type instanceof ParameterizedType) {
            this.classType = (Class<T>) ((ParameterizedType) this.type).getRawType();
        } else {
            this.classType = (Class<T>) this.type;
        }
    }
}

