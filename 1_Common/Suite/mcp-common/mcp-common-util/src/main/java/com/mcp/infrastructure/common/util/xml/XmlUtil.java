package com.mcp.infrastructure.common.util.xml;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: XmlUtil
 * @Package com.jnj.autocloud.common.util.xml
 * @Description: xml util
 * @date 2018/9/10 16:48
 */
public class XmlUtil {
    public static String transformStringToXml(String xmlString) {
        /*String xml = xmlString.replaceAll("&lt;", "<");
        xml = xml.replaceAll("&quot;", "\"");
        xml = xml.replaceAll("&#39;", "'");
        xml = xml.replaceAll("&#61;", "=");
        xml = xml.replaceAll("&gt;", ">");
        xml = xml.replaceAll("&#10;", "");
        xml = xml.replaceAll("\n", "");*/

        return StringEscapeUtils.unescapeXml(xmlString);
    }
}
