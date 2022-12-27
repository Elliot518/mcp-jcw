package com.mcp.toolkit.infrastructure.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : KG
 * description:
 * create date: 4:01 PM 2020/1/3
 * modified by:
 */

public class RegexJudger {
    public static boolean match(String pattern, String destStr) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(destStr);

        return m.matches();
    }
}
