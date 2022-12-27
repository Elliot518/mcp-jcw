package com.mcp.infrastructure.common.domain.dto.user;

import com.mcp.infrastructure.common.domain.biz.permission.PermissionTree;
import lombok.Data;

/**
 * @author: KG
 * @description:
 * @date: Created in 7:37 下午 2020/6/28
 * @modified by:
 */
@Data
public class PlatformUserDto {
    /**
     * 用户ID - UUID
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码(MD5加密)
     */
    private String password;

    /**
     * 来自平台ID
     */
    private String platformId;

    /**
     * 来自系统ID
     */
    private Integer systemId;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 上一次登陆获取的令牌
     */
    private String lastToken;

    /**
     * 权限树
     */
    private PermissionTree permissionTree;
}


