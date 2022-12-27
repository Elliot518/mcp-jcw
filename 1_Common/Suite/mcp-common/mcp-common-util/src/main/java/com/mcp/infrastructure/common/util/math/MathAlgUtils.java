package com.mcp.infrastructure.common.util.math;

/**
 * @author: KG
 * @description: 数学算法工具
 * @date: Created in 5:10 下午 2020/7/2
 * @modified by:
 */

public class MathAlgUtils {
    /**
     * 除法算法: 找到与被除数最接近的整数
     * @param dividend
     * @param divisor
     * @return
     */
    public static int findNearestLessDivisionInt(int dividend, int divisor) {
        if (dividend % divisor == 0) {
            return dividend;
        }

        int times = dividend / divisor;
        int lessNum = divisor * times;

        return lessNum;
    }

    /**
     * 除法算法: 找到与被除数最接近的整数
     * @param dividend
     * @param divisor
     * @return
     */
    public static int findNearestDivisionInt(int dividend, int divisor) {
        if (dividend % divisor == 0) {
            return dividend;
        }

        int times = dividend / divisor;
        int lessNum = divisor * times;
        int aboveNum = divisor * (times + 1);

        if (Math.abs(dividend - lessNum) > Math.abs(aboveNum - dividend)) {
            return aboveNum;
        }

        return lessNum;
    }

    public static int allocateTrackByGroupInTurn(int groupNo, int trackNum) {
        int trackNo = groupNo % trackNum;
        if (trackNo == 0) {
            return trackNum;
        }

        return trackNo;
    }

    /**
        测试: findNearestDivisionInt
     */
    private static void testFindNearestLessDivisionInt() {
        int dividend = 37;
        int divisor = 8;
        int nearNum = findNearestLessDivisionInt(dividend, divisor);
        System.out.printf("Nearest less number of %d divided by %d is: %d", dividend, divisor, nearNum);
    }

    public static void main(String[] args) {
        testFindNearestLessDivisionInt();
    }
}



