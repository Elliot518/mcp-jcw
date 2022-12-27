package com.mcp.infrastructure.common.util.time;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : KG
 * description:
 * create date: 9:53 PM 2020/1/21
 * modified by:
 */

public class CalendarUtils {
    public static int monthIntToCalendar(int month) {
        int calendar;
        switch (month) {
            case 1:
                calendar = Calendar.JANUARY;
                break;
            case 2:
                calendar = Calendar.FEBRUARY;
                break;
            case 3:
                calendar = Calendar.MARCH;
                break;
            case 4:
                calendar = Calendar.APRIL;
                break;
            case 5:
                calendar = Calendar.MAY;
                break;
            case 6:
                calendar = Calendar.JUNE;
                break;
            case 7:
                calendar = Calendar.JULY;
                break;
            case 8:
                calendar = Calendar.AUGUST;
                break;
            case 9:
                calendar = Calendar.SEPTEMBER;
                break;
            case 10:
                calendar = Calendar.OCTOBER;
                break;
            case 11:
                calendar = Calendar.NOVEMBER;
                break;
            case 12:
                calendar = Calendar.DECEMBER;
                break;
            default:
                calendar = Calendar.JANUARY;
        }

        return calendar;
    }

    public static LocalDateTime convertStringDateToLocalDateTime(String strDate) {
        int year, month, day;
        int calendarMonth;

        String pattern = "(\\d+)-(\\d+)月-(\\d{2})";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(strDate);
        if (matcher.find()) {
            String[] arrDates = strDate.split("-");
            if (arrDates.length == 3) {
                day = Integer.parseInt(arrDates[0]);
                int idx = arrDates[1].indexOf("月");
                if (idx < 0) {
                    return null;
                }
                month = Integer.parseInt(arrDates[1].substring(0, idx));
                calendarMonth = CalendarUtils.monthIntToCalendar(month);
                year = Integer.parseInt("20" + arrDates[2]);
                return convertLocalDateTimeFromCalendar(year, calendarMonth, day);
            }
        } else if (strDate.length() >=10) {
            String strDate2 = strDate.substring(0,10);
            return DateTimeUtils.strToLocalDateTime(strDate2);
        }

        return null;
    }

    public static LocalDateTime convertLocalDateTimeFromCalendar(int year, int calendarMonth, int day) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.set(year, calendarMonth, day);
        return gc.toZonedDateTime().toLocalDateTime();
    }

    public static void main(String[] args) {
        String strDate = "12-12月-05";
        LocalDateTime localDateTime = convertStringDateToLocalDateTime(strDate);
        System.out.println("GregorianCalendar 转为 LocalDateTime: " + localDateTime);
    }
}
