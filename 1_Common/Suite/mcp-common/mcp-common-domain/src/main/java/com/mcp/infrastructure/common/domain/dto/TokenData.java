package com.mcp.infrastructure.common.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : KG
 * description:
 * create date: 6:38 PM 2020/3/11
 * modified by:
 */
@Data
public class TokenData<T> implements Serializable {
    private String prefix;

    private T data;
}

