package com.mcp.infrastructure.common.util.generator;

import com.mcp.infrastructure.common.util.time.DateTimeUtils;

import java.util.Date;
import java.util.UUID;

public class IdGenerator {
    private static final String timePattern = "yyyyMMddHHmmssSSS";

    /**
     * 根据UUID(忽略中划线)生成ID
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 根据当前时间(精确到毫秒)生成时间戳ID
     * @return
     */
    public static String getIdByTime() {
        Date date = new Date();
        return DateTimeUtils.date2String(date, timePattern);
    }

    public static void main(String[] args) {
        //String uuid = UUID.randomUUID().toString();
        String uuid = getIdByTime();
        System.out.println(uuid);
    }
}
