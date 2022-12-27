package com.mcp.infrastructure.common.util.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author : KG
 * description:
 * create date: 9:36 AM 2020/4/2
 * modified by:
 */

public class NumUtils {
    private static final String PERCENT_FLAG = "%";

    public static List<BigDecimal> getDiffList(List<BigDecimal> dataList, int startIndex) {
        int len = dataList.size();
        List<BigDecimal> diffList = new ArrayList<>();
        for (int i = startIndex; i < len; i++) {
            BigDecimal currentElem = dataList.get(i);
            BigDecimal previousElem = dataList.get(i - 1);
            BigDecimal diff = currentElem.subtract(previousElem);
            diffList.add(diff);
        }

        return diffList;
    }

    public static Integer getUpsFromDiffList(List<BigDecimal> diffList) {
        return diffList.stream().filter(e -> e.compareTo(BigDecimal.ZERO) < 0).reduce(BigDecimal.ZERO, BigDecimal::add).intValue();
    }

    public static Integer getDownsFromDiffList(List<BigDecimal> diffList) {
        return diffList.stream().filter(e -> e.compareTo(BigDecimal.ZERO) > 0).reduce(BigDecimal.ZERO, BigDecimal::add).intValue();
    }

    public static Integer getChangeSum(List<BigDecimal> dataList, int startIndex) {
        int len = dataList.size();
        BigDecimal sum = BigDecimal.ZERO;
        for (int i = startIndex; i < len; i++) {
            BigDecimal currentElem = dataList.get(i);
            BigDecimal previousElem = dataList.get(i - 1);
            if (currentElem.compareTo(previousElem) != 0) {
                sum = sum.add(currentElem);
            }
        }

        return sum.intValue();
    }

    public static List<BigDecimal> getNoDuplicateList(List<BigDecimal> dataList) {
        LinkedHashSet<BigDecimal> hashSet = new LinkedHashSet<>(dataList);

        return new ArrayList<>(hashSet);
    }

    public static String getHistoryOfNoDuplicates(List<BigDecimal> dataList) {
        List<BigDecimal> noDupList = getNoDuplicateList(dataList);

        String history = "";
        int len = noDupList.size();
        for (int i = 0; i < len; i++) {
            if (i<len-1) {
                history = history + noDupList.get(i) + ",";
            } else {
                history = history + noDupList.get(i);
            }
        }

        return "[" + history + "]";
    }

    public static BigDecimal getAverageOfNoDuplicates(List<BigDecimal> dataList) {
        List<BigDecimal> noDupList = getNoDuplicateList(dataList);

        int len = noDupList.size();
        BigDecimal sumAll = noDupList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        return sumAll.divide(new BigDecimal(len), 10, BigDecimal.ROUND_HALF_DOWN);
    }

    public static BigDecimal convertPercentStringToDecimal(String percent) {
        String strPercent = percent.trim();
        if ("".equals(strPercent)) {
            return BigDecimal.ZERO;
        }

        if (strPercent.length() <= 1) {
            return BigDecimal.ZERO;
        }

        String percentFlag = strPercent.substring(strPercent.length() - 1);
        if (!PERCENT_FLAG.equals(percentFlag)) {
            return BigDecimal.ZERO;
        }

        String percentNum = strPercent.substring(0, strPercent.length() - 1);

        return new BigDecimal(percentNum).divide(BigDecimal.valueOf(100), 10, BigDecimal.ROUND_HALF_DOWN);
    }

    public static String intToStringAddZeroLeft(int num, int length) {
        return String.format("%0" + length + "d", num);
    }

    private static void testConvertPercentStringToDecimal() {
        String percent = "25%";

        System.out.println(convertPercentStringToDecimal(percent));
    }

    public static void main(String[] args){
        String strNum = intToStringAddZeroLeft(10, 10);
        System.out.println(strNum);
    }
}

