package com.mcp.infrastructure.common.util.sign;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: KG
 * @description:
 * @date: Created in 2:14 下午 2020/10/20
 * @modified by:
 */

public class ApiSignUtils {
    private static final String SIGN = "sign";

    /**
     ** 签名算法
     ** @param o 要参与签名的数据对象
     ** @return 签名
     ** @throws IllegalAccessException
     *      
     */
    public static String getSign(Object o) throws IllegalAccessException {
        ArrayList<String> list = new ArrayList<>();
        Class cls = o.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            if (f.get(o) != null && f.get(o) != "") {
                addSignItem(f, o, list);
            }
        }

        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
        }

        String result = sb.toString();

        // remove last &
        result = result.substring(0, result.length() - 1);

        return result;
    }

    private static void addSignItem(Field f, Object o, List<String> fieldList) throws IllegalAccessException {
        if (!SIGN.equals(f.getName())) {
            String fieldItem = f.getName() + "=" + f.get(o) + "&";

            fieldList.add(fieldItem);
        }
    }
}




