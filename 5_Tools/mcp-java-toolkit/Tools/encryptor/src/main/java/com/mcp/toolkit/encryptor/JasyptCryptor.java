package com.mcp.toolkit.encryptor;

import com.mcp.toolkit.encryptor.constants.CryptConst;
import com.mcp.toolkit.infrastructure.util.JasyptUtil;
import com.mcp.toolkit.infrastructure.util.PropertyUtils;

/**
 * @author: KG
 * @description: 非对称加密
 * @date: Created in 9:34 AM 2020/3/11
 * @modified by:
 */

public class JasyptCryptor {
    public static void main(String[] args) {
        String dbSalt = PropertyUtils.getProperty(CryptConst.CONFIG_FILE, CryptConst.DB_SALT);

        String dbUrl = PropertyUtils.getProperty(CryptConst.CONFIG_FILE, CryptConst.DB_URL);
        String dbUser = PropertyUtils.getProperty(CryptConst.CONFIG_FILE, CryptConst.DB_USER);
        String dbPass = PropertyUtils.getProperty(CryptConst.CONFIG_FILE, CryptConst.DB_PASS);

        String uploadFolder = PropertyUtils.getProperty(CryptConst.CONFIG_FILE, CryptConst.UPLOAD_FOLDER);

        // 加密
        System.out.println(String.format("encrypt url: %s", JasyptUtil.encyptPwd(dbSalt, dbUrl)));
        System.out.println(String.format("encrypt user: %s", JasyptUtil.encyptPwd(dbSalt, dbUser)));
        System.out.println(String.format("encrypt password: %s", JasyptUtil.encyptPwd(dbSalt, dbPass)));

        System.out.println(String.format("encrypt upload folder: %s", JasyptUtil.encyptPwd(dbSalt, uploadFolder)));

        // 解密 (这里放开可以做验证)
        System.out.println(String.format("decrypt url: %s", JasyptUtil.decyptPwd(dbSalt, "P3YBicVgje3mxJczeKunG9ZMau8V/LWOVtvJMnCZVXS/LX8rBnBkgpdWn8cIo+9ByamaaolDMpwKomOI4YpiLz/G1MveTf2590W+EGhzYjGh56SoDACuAnIgS/Ek4eyvaPvSRJloBdNMPgdwi+ndmSVMje8B5vUTeTrYQMrh1ybwmwnAS00B0FB3Fr/3xVsausg8PyZSOqRuA5EvXPq54Q==")));
        System.out.println(String.format("decrypt user: %s", JasyptUtil.decyptPwd(dbSalt, "QYOkvE8KEhKko96aJ5bjjg==")));
        System.out.println(String.format("decrypt password: %s", JasyptUtil.decyptPwd(dbSalt, "mNy3ZN1/TOTOwILfWApCdA==")));

        System.out.println(String.format("decrypt upload folder: %s", JasyptUtil.decyptPwd(dbSalt, "K+k6r678EkiMnOZaBUm8SBPwLvcdLKd6")));
    }
}

