package com.mcp.infrastructure.common.domain.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: KG
 * @description:
 * @date: Created in 5:22 下午 2020/8/7
 * @modified by:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private Integer status;
    private String clientId;

    /**
     * PDA用户组(temporarily)
     */
    private String pdaGroup;

    private List<String> roles;
}

