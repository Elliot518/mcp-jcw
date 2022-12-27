package com.mcp.infrastructure.common.util.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 * @author: KG
 * @description:
 * @date: Created in 3:09 下午 2020/7/2
 * @modified by:
 */

public class RandUtils {
    /**
     * 生成一定范围的随机整数
     * @param min
     * @param max
     * @return
     */
    public static int createIntFromScope(int min, int max) {
        Random rand = new Random();

        return rand.nextInt(max) % (max - min + 1) + min;
    }

    /**
     * 生成一定范围的随机浮点数, 指定小数点位数
     * @param min
     * @param max
     * @param scale 精度
     * @return
     */
    public static BigDecimal createBigDecimalFromScope(float min, float max, int scale) {
        BigDecimal bd = new BigDecimal(Math.random() * (max - min) + min);

        return bd.setScale(scale, RoundingMode.HALF_UP);
    }

    /**
     * 生成一定位数每位都是的0-9的随机数字字符串
     * @param capacity
     * @return
     */
    public static String createNumberStrByDigitCap(int capacity) {
        StringBuilder sb = new StringBuilder();

        Random rand = new Random();
        for (int i=0;i<capacity;i++) {
            int digit = rand.nextInt(10);
            sb.append(digit);
        }

        return sb.toString();
    }

    /**
     * 根据索引位和长度生成每位都是的0-9的顺序循环数字字符串
     * @param index
     * @param length
     * @return
     */
    public static String createSeqNumStrByIndexAndLength(int index, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i=index;i<index + length;i++) {
            int digit = i % 10;
            sb.append(digit);
        }

        return sb.toString();
    }
}



















