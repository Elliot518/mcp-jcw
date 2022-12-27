package com.mcp.infrastructure.common.util.geo;

import org.apache.commons.lang.StringUtils;

/**
 * @author: KG
 * @description:
 * @date: Created in 4:10 PM 2019/10/12
 * @modified by:
 */

public class ChinaGeoUtils {
    private static String CITY_SUFFIX = "市";

    public static String getStandardChinaProvince(String province) {
        String prov = StringUtils.trim(province);
        switch (prov) {
            case "上海":
            case "北京":
            case "天津":
            case "重庆":
                prov = prov + "市";
                break;
            case "内蒙古":
            case "西藏":
                prov = prov + "自治区";
                break;
            case "宁夏":
                prov = prov + "回族自治区";
                break;
            case "新疆":
                prov = prov + "维吾尔自治区";
                break;
            case "广西":
                prov = prov + "壮族自治区";
                break;
            case "香港":
            case "澳门":
                prov = prov + "特别行政区";
                break;
            default:
                prov = prov + "省";
        }

        return prov;
    }

    public static String getStandardChinaCity(String pCity) {
        String city = StringUtils.trim(pCity);
        if ("内蒙".equals(city)) {
           city = "内蒙古";
        }
        city = city.replace("外围", "");
        if (!city.contains(CITY_SUFFIX)) {
            city = city + CITY_SUFFIX;
        }

        return city;
    }
}

