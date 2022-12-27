package org.genesis.toolbox.beans.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.genesis.toolbox.beans.service.ADFRestService;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

public class ADFRestServiceImpl implements ADFRestService {


    private static String USERNAME;

    private static String PASSWORD;

    private static String dataServiceUrl;

    private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000).setConnectionRequestTimeout(60000).build();

    static {
        Properties prop = new Properties();
        try {
            // 通过classpath去读取properties文件，只会找classpath下面的文件，不会递归查找
            Properties properties = new Properties();
            properties.load(ADFRestServiceImpl.class.getResourceAsStream("/config/adf/env.properties"));
            USERNAME = properties.getProperty("login.name");
            PASSWORD = properties.getProperty("login.password");
            dataServiceUrl = properties.getProperty("dataserviceUrl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //return token
    @Override
    public String login() {
        String token = null;
        CloseableHttpResponse response = null;
        try {
            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setDefaultRequestConfig(requestConfig).build();
            // 创建http POST请求
            HttpPost httpPost = new HttpPost("https://" + dataServiceUrl + "/adf-data-service/login");
            List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
            parameters.add(new BasicNameValuePair("username", USERNAME));
            parameters.add(new BasicNameValuePair("password", PASSWORD));
            UrlEncodedFormEntity formEntity = null;
            try {
                formEntity = new UrlEncodedFormEntity(parameters);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            httpPost.setEntity(formEntity);
            response = httpClient.execute(httpPost);
            // 判断返回状态是否为200
            //200: 返回成功 toekn
            //201: 用户名密码密码 token:0
            //404: 路径错误 token:-1
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                //parse httpEntity using json node
                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.readTree(content);
                if (node.get("statusCode").asText().equals("200")) {
                    token = node.get("value").get("token").asText();
                } else if (node.get("statusCode").asText().equals("201")) {
                    token = "0";
                } else {
                    token = "-1";
                }
            } else if (response.getStatusLine().getStatusCode() == 404) {
                token = "-1";
            }
        } catch (Exception e) {
            token = "-1";
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return token;
        }
    }

    //get the valid path
    public String getValidPath(String path, String token) {
        String validPath = null;
        if (path.indexOf('/') == -1) {
            if (count("/omp/" + path, token) != -1) {
                return "/omp/" + path;
            } else if (count("/plan/" + path, token) != -1) {
                return "/plan/" + path;
            } else if (count("/edm/" + path, token) != -1) {
                return "/edm/" + path;
            } else {
                return path;
            }
        } else {
            return path;
        }
    }

    // get the count of the region
    // -1 region 不存在
    // -2 没权限
    // -3 unknown error
    //todo count type : int -> long

    public int count(String path, String token) {
        int count = 0;
        CloseableHttpResponse response = null;
        try {
            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setDefaultRequestConfig(requestConfig).build();
            // 创建http GET请求
            URIBuilder uriBuilder = new URIBuilder("https://" + dataServiceUrl + "/adf-data-service/service/query/searchData");
            uriBuilder.addParameter("token", token);
            uriBuilder.addParameter("path", path);
            HttpGet httpGet = new HttpGet(uriBuilder.build());

            response = httpClient.execute(httpGet);
            // 判断返回状态是否为200
            //200: 返回成功 toekn
            //201: 用户名密码密码 token:0
            //404: 路径错误 token:-1
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                //parse httpEntity using json node
                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.readTree(content);
                if (node.get("statusCode").asText().equals("200")) {
                    count = node.get("totalCount").asInt();
                } else if (node.get("statusCode").asText().equals("201")) {
                    if (node.get("statusMsg").asText().contains("ServerOperationException")) {
                        count = -1;
                    }
                    if (node.get("statusMsg").asText().contains("Access Token is expired. Please login again")) {
                        count = -2;
                    }
                }
            } else {
                count = -3;
            }
        } catch (Exception e) {
            count = -3;
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return count;

        }
    }

    //get the column
    //get the column if count > 0
    @Override
    public Set<String> getColumn(String path, String token) {
        Set<String> res = new HashSet<String>();
        CloseableHttpResponse response = null;
        try {
            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setDefaultRequestConfig(requestConfig).build();
            // 创建http GET请求
            URIBuilder uriBuilder = new URIBuilder("https://" + dataServiceUrl + "/adf-data-service/service/query/searchData");
            uriBuilder.addParameter("token", token);
            uriBuilder.addParameter("path", path);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.readTree(content);
                if (node.get("statusCode").asText().equals("200")) {
                    String column = node.get("values").findValues("value").get(0).toString();
                    Map<String, String> result = new ObjectMapper().readValue(column, Map.class);
                    res = result.keySet();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return res;
        }
    }

    //创建SSL安全连接
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {

                public boolean verify(String arg0, SSLSession arg1) {

                    return true;
                }

                public void verify(String host, SSLSocket ssl) throws IOException {
                }

                public void verify(String host, X509Certificate cert) throws SSLException {
                }

                public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
                }
            });
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return sslsf;
    }
}
