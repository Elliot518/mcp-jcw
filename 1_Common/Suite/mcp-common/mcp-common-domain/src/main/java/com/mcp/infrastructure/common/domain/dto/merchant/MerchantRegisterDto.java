package com.mcp.infrastructure.common.domain.dto.merchant;

import lombok.Data;

/**
 * @author: KG
 * @description:
 * @date: Created in 9:16 上午 2020/7/2
 * @modified by:
 */
@Data
public class MerchantRegisterDto {
    /**
     * 商户ID(UUID)
     */
    private String merchantId;

    /**
     * 商户用户ID(UUID)
     */
    private String merchantUserId;
}
