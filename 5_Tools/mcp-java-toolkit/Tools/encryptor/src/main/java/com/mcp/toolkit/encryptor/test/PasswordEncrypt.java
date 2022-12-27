package com.mcp.toolkit.encryptor.test;

import com.mcp.toolkit.infrastructure.util.EncryptUtil;

/**
 * @author : KG
 * description:
 * create date: 9:00 PM 2020/3/11
 * modified by:
 */

public class PasswordEncrypt {
    public static void main(String[] args){
        String salt = "sh1e2r3ys4s5o6f7t8y";
        String password = "123456";
        String encryptPassword = EncryptUtil.DeMD5Crypt(password, salt);
        System.out.println("encrypt password: " + encryptPassword);
    }
}
