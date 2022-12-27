package com.mcp.infrastructure.common.util.http;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author: KG
 * @description:
 * @date: Created in 12:53 PM 2019/5/2
 * @modified by:
 */

public class ExportUtil {
    public static void downloadExcel(HttpServletResponse response, InputStream inputStream, String fileName) {
        byte[] buffer = new byte[10240];

        try {
            // 告诉浏览器用什么软件可以打开此文件
            response.setHeader("content-Type", "application/vnd.ms-excel");
            // 下载文件的默认名称
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try (
                OutputStream output = response.getOutputStream();
        ) {
            for (int length = 0; (length = inputStream.read(buffer)) > 0; ) {
                output.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}








