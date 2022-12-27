package com.mcp.infrastructure.common.util.generator;

/**
 * @author : KG
 * description:
 * create date: 10:13 PM 2019/11/28
 * modified by:
 */

public class TokenGenerator {
    public static String ClientUser = "C";
    public static String MerchantUser = "M";

    public static String generateToken(String category) {
        return category + "_" + IdGenerator.getUUID();
    }

    // not used
    public static String generateTokenKey(String prefix, String terminal, String system, String strToken) {
        return prefix + "_" + terminal + "_" + system + "_" + strToken;
    }
}
