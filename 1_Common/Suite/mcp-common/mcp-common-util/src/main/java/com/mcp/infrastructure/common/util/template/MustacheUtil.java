package com.mcp.infrastructure.common.util.template;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: MustacheUtil
 * @Package com.jnj.autocloud.common.util.template
 * @Description: mustache util
 * @date 2018/9/7 10:23
 */
public class MustacheUtil {
    public static <T> String convertListToString(String tempPath, String key, T object) {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile(tempPath);

        Map<String, Object> context = new HashMap<>();
        context.put(key, object);

        StringWriter writer = new StringWriter();
        String html = "";
        try {
            m.execute(writer, context).flush();
            html = writer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return html;
    }

    public static <T> String convertObjectToString(String tempPath, T object) {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile(tempPath);

        StringWriter writer = new StringWriter();
        try {
            m.execute(writer, object).flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String html = writer.toString();

        return html;
    }
}

