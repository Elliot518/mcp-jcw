package com.mcp.infrastructure.common.domain.biz.permission;

import lombok.Data;

import java.util.List;

/**
 * @author: KG
 * @description:
 * @date: Created in 5:25 下午 2020/6/28
 * @modified by:
 */
@Data
public class MenuGroup {
    private Long id;

    private String name;

    private List<MenuItem> subMenus;
}
