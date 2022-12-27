package com.mcp.infrastructure.common.domain.helper;

import cn.hutool.json.JSONUtil;
import com.mcp.infrastructure.common.domain.api.CommonResult;
import com.mcp.infrastructure.common.domain.api.CommonResultCode;
import com.mcp.infrastructure.common.domain.code.BaseCode;
import com.mcp.infrastructure.common.domain.dto.auth.Oauth2TokenDto;
import com.mcp.infrastructure.common.domain.dto.auth.UserDto;
import com.mcp.infrastructure.common.domain.exception.ApiException;
import com.mcp.infrastructure.common.domain.request.AuthLoginReq;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: KG
 * @description:
 * @date: Created in 4:11 下午 2020/9/8
 * @modified by:
 */

public class AuthHelper {
    public static Map<String, String> getAuthRequestMap(AuthLoginReq req) {
        Map<String, String> params = new HashMap<>();
        params.put("client_id", req.getClientId());
        params.put("client_secret", req.getClientSecret());
        params.put("grant_type", "password");
        params.put("username", req.getUsername());
        params.put("password", req.getPassword());

        return params;
    }

    public static CommonResult<Oauth2TokenDto> convertAuthResult(CommonResult<HashMap<String, String>> commonResult) {
        CommonResult<Oauth2TokenDto> finalResult = new CommonResult<>(commonResult.getCode(), commonResult.getMessage());

        if (!BaseCode.Success.CODE.equals(commonResult.getCode())) {
            return finalResult;
        }

        Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder().build();
        HashMap<String, String> tokenMap = commonResult.getData();
        oauth2TokenDto.setToken(tokenMap.get("token"));
        oauth2TokenDto.setRefreshToken(tokenMap.get("refreshToken"));
        oauth2TokenDto.setTokenHead(tokenMap.get("tokenHead"));
        finalResult.setData(oauth2TokenDto);

        return finalResult;
    }


    public static UserDto convertUserFromTokenHeader(String userTokenStr) {
        if(StringUtils.isEmpty(userTokenStr)){
            throw new ApiException(CommonResultCode.UNAUTHORIZED);
        }

        return JSONUtil.toBean(userTokenStr, UserDto.class);
    }
}
