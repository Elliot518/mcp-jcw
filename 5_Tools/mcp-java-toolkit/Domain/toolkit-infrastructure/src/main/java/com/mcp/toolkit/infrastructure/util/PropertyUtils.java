package com.mcp.toolkit.infrastructure.util;

import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author: KG
 * @description:
 * @date: Created in 4:31 下午 2020/7/26
 * @modified by:
 */
@Slf4j
public class PropertyUtils {
    private static Properties props;

//    static {
//        loadProps();
//    }

    synchronized static private void loadProps(String path) {
        //String propertiesName = "common.properties";
        props = new Properties();
        try (InputStream in = PropertyUtils.class.getClassLoader().getResourceAsStream(path)) {
            props.load(in);
        } catch (FileNotFoundException e) {
            log.error("文件: " + path + "未找到!");
        } catch (IOException e) {
            log.error("出现IOException");
        }
    }

    public static String getProperty(String path, String key) {
        return getProperty(path, key, "");
    }

    public static String getProperty(String path, String key, String defaultValue) {
        if (null == props) {
            loadProps(path);
        }
        return props.getProperty(key, defaultValue);
    }
}



