package com.mcp.infrastructure.common.util.fundamental;

/**
 * @author : KG
 * description: StringUtils的一些函数增强和补充
 * create date: 2:20 PM 2019/11/14
 * modified by:
 */

public class StringPlusUtils {
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        } else {
            int sz = str.length();

            for (int i = 0; i < sz; ++i) {
                if (!Character.isDigit(str.charAt(i)) && str.charAt(i) != '.') {
                    return false;
                }
            }

            return true;
        }
    }

    public static String getIntegerPart(String strNum) {
        int idxPoint = strNum.indexOf(".");

        if (idxPoint > 0) {
            return strNum.substring(0, idxPoint);
        }

        return strNum;
    }

    public static Boolean isIntegerExceed(String strNum, int maxLen) {
        String strIntPart = getIntegerPart(strNum);

        return strIntPart.length() > maxLen;
    }

//    public static void main(String args[]) {
//        System.out.println(getIntegerPart("20020181055"));
//        System.out.println(isIntegerExceed("20020181055", 10));
//    }
}
