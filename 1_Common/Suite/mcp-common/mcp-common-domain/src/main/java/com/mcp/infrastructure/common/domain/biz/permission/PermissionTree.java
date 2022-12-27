package com.mcp.infrastructure.common.domain.biz.permission;

import lombok.Data;

import java.util.List;

/**
 * @author: KG
 * @description:
 * @date: Created in 7:49 下午 2020/6/28
 * @modified by:
 */
@Data
public class PermissionTree {

    private String roleName;

    private List<MenuGroup> menuGroups;

}

