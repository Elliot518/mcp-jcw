package com.mcp.toolkit.infrastructure.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * VerifyPhoneNumber class
 *
 * @author ZhangLei
 * @date 2017/12/4
 */
public class RegUtil {
    /**
     * 验证手机号
     */
    private static Pattern NUMBER_PATTERN = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");

    public Boolean verifyPhoneNumber(String phoneNumber) {
        Pattern p = null;
        Matcher m = null;
        boolean b = true;
        p = NUMBER_PATTERN;
        m = p.matcher(phoneNumber);
        b = m.matches();
        if (b) {
            return b;
        }
        return false;
    }
}
