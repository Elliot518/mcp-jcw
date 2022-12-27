package com.mcp.infrastructure.common.util.aliyun.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;

import java.io.*;
import java.net.URL;
import java.util.Date;

/**
 * OssUtil class
 *
 * @author ZhangLei
 * @date 2017/12/4
 */
public class OssUtils {

    public static void delObject(String bucketName, String key, OSSClient ossClient) {
        ossClient.deleteObject(bucketName, key);
    }

    /**
     * 下载文件流
     *
     * @param bucketName
     * @param key
     * @return
     */
    public static File getInputStream(String bucketName, String key) {
        // 创建OSSClient实例
        OSSClient ossClient = connect();
        OSSObject ossObject = ossClient.getObject(bucketName, key);
        InputStream content = ossObject.getObjectContent();
        File file = new File("h://d.jpg");
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = content.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            content.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        shutdown(ossClient);
        return file;
    }

    /**
     * 上传文件流
     *
     * @param bucketName
     * @param key
     * @param inputStream
     */
    public static void putInputStream(String bucketName, String key, InputStream inputStream) {
        // 创建OSSClient实例
        OSSClient ossClient = connect();
        ossClient.putObject(bucketName, key, inputStream);
        shutdown(ossClient);
    }

    /**
     * 下载文件到本地
     *
     * @param bucketName
     * @param key
     * @param filePath
     */
    public static void getFile(String bucketName, String key, String filePath) {
        // 创建OSSClient实例
        OSSClient ossClient = connect();
        ossClient.getObject(new GetObjectRequest(bucketName, key), new File(filePath));
        shutdown(ossClient);
    }

    /**
     * 上传文件
     *
     * @param bucketName
     * @param key
     * @param file
     * @param ossClient
     */
    public static void putFile(String bucketName, String key, File file, OSSClient ossClient) {
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentType("image/jpeg");
        // 创建OSSClient实例
        ossClient.putObject(bucketName, key, file, meta);
    }

    /**
     * 上传文件
     *
     * @param bucketName
     * @param key
     * @param filePath
     */
    public static void putFileByPath(String bucketName, String key, String filePath) {
        // 创建OSSClient实例
        OSSClient ossClient = connect();
        ossClient.putObject(bucketName, key, new File(filePath));
        shutdown(ossClient);
    }

    /**
     * 下载字符串
     *
     * @param bucketName
     * @param key
     * @return
     * @throws IOException
     */
    public static String getString(String bucketName, String key) throws IOException {
        // 创建OSSClient实例
        OSSClient ossClient = connect();
        OSSObject ossObject = ossClient.getObject(bucketName, key);
        InputStream content = ossObject.getObjectContent();
        StringBuffer sbf = new StringBuffer();
        if (content != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            while (true) {
                String line = reader.readLine();
                if (line != null) {
                    sbf.append(line);
                }
                if (line == null) break;
                System.out.println("\n" + line);
            }
            content.close();
        }
        String str = sbf.toString();
        shutdown(ossClient);
        return str;
    }

    /**
     * 上传字符串
     *
     * @param bucketName
     * @param key
     * @param str
     */
    public static void putString(String bucketName, String key, String str) {
        // 创建OSSClient实例
        OSSClient ossClient = connect();
        ossClient.putObject(bucketName, "abc", new ByteArrayInputStream(str.getBytes()));
        shutdown(ossClient);
    }

    /**
     * 关闭连接
     *
     * @param client
     */
    public static void shutdown(OSSClient client) {
        client.shutdown();
    }

    /**
     * 创建连接
     *
     * @param endPoint
     * @param accessKeyId
     * @param accessKeySecret
     * @return
     */
    public static OSSClient connect(String endPoint, String accessKeyId, String accessKeySecret) {
        OSSClient client = new OSSClient(endPoint, accessKeyId, accessKeySecret);
        return client;
    }

    /**
     * 创建连接
     *
     * @return
     */
    public static OSSClient connect() {
        OSSClient client = new OSSClient("", "", "");
        return client;
    }

    /**
     * 获取图片url
     *
     * @param bucketName
     * @param key
     * @param ossClient
     * @return
     */
    public static String getImgURl(String bucketName, String key, OSSClient ossClient) {
        // 30 minute to expire
        Date expires = new Date(new Date().getTime() + 10 * 365 * 24 * 3600 * 1000);
        GeneratePresignedUrlRequest generatePresignedUrlRequest;
        generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, key);
        generatePresignedUrlRequest.setExpiration(expires);
        URL url = ossClient.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();
    }


}
