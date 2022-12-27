package com.mcp.infrastructure.common.domain.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author KG
 * @description 分页数据
 * @date Created in 2021年09月24日 9:59 AM
 * @modified_by
 */
@Data
@NoArgsConstructor
public class PageData<T> {
    /**
        总条数
    */
    private long totalCount;

    /**
        当前页
     */
    private long currentPage;

    /**
        当页数据
     */
    private List<T> data;
}
