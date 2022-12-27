package com.mcp.infrastructure.common.util.message;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 使用apache httpclient 4.3以上的版本jar 提供httpclient连接工具类
 * 
 * @author YangKui
 * 
 */
public class HttpClientUtil {

	private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	private static PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
	static {
		cm.setMaxTotal(800);
		cm.setDefaultMaxPerRoute(800);
	}

	private String statusCode = "200";
	/**
	 * 通过get方式获取指定地址的内容
	 * 
	 * @param url
	 *            需要访问的地址如：http://www.baidu.com
	 * @param chartset
	 *            字符编码，将地址返回的内容进行字符编码，如果为空则默认为：UTF-8
	 * @return 地址对应的内容
	 */
	public static String get(String url, int socketTime, int connectTimeout, String chartset)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(cm).build();
		RequestConfig requetConfig = RequestConfig.custom().setSocketTimeout(socketTime)
				.setConnectTimeout(connectTimeout).build();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(requetConfig);
		CloseableHttpResponse response1 = null;
		try {
			response1 = httpclient.execute(httpGet);
			HttpEntity entity1 = response1.getEntity();
			if (chartset == null || "".equals(chartset)) {
				chartset = "UTF-8";
			}
			String responseBody = EntityUtils.toString(entity1, chartset);
			EntityUtils.consume(entity1);
			StatusLine statusLine = response1.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode != 200) {
				logger.error("current request url error,satusCode:{},responseBody:{}", statusCode, responseBody);
				throw new IOException("request url statusCode is 500!");
			}
			return responseBody;
		} finally {
			if (response1 != null) {
				response1.close();
			}
		}
	}

	/**
	 * 使用post方式提交参数
	 * 
	 * @param url
	 * @param params
	 *            提交的参数已key,value的形式保存在map当中
	 * @param socketTime
	 * @param connectTimeout
	 * @param chartset
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String post(String url, Map<String, String> params, int socketTime, int connectTimeout,
                              String chartset) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(cm).build();
		RequestConfig requetConfig = RequestConfig.custom().setSocketTimeout(socketTime)
				.setConnectTimeout(connectTimeout).build();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requetConfig);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, chartset));
		CloseableHttpResponse response1 = null;
		try {
			response1 = httpclient.execute(httpPost);
			HttpEntity entity1 = response1.getEntity();
			// do something useful with the response body
			// and ensure it is fully consumed
			if (chartset == null || "".equals(chartset)) {
				chartset = "UTF-8";
			}
			String responseBody = EntityUtils.toString(entity1, chartset);
			EntityUtils.consume(entity1);
			StatusLine statusLine = response1.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode != 200) {
				logger.error("current request url error,satusCode:{},responseBody:{}", statusCode, responseBody);
				throw new IOException("request url statusCode is 500!");
			}
			return responseBody;
		} finally {
			if (response1 != null) {
				response1.close();
			}
		}
	}

	/**
	 * 使用xml格式提交请求
	 * 
	 * @param url
	 *            请求路径
	 * @param content
	 *            xml报文
	 * @param socketTime
	 *            连接时间（单位毫秒）
	 * @param connectTimeout
	 *            连接等待时间（单位毫秒）
	 * @param reqChartset
	 *            请求报文字符编码，默认为UTF-8
	 * @param respChartset
	 *            返回报文字符编码，默认为UTF-8
	 * @param contentType
	 *            http内容类型
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String postXml(String url, String content, int socketTime, int connectTimeout, String reqChartset,
                                 String respChartset, String contentType) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(cm).build();
		RequestConfig requetConfig = RequestConfig.custom().setSocketTimeout(socketTime)
				.setConnectTimeout(connectTimeout).build();
		HttpPost httpPost = new HttpPost(url);

		httpPost.setConfig(requetConfig);
		if (reqChartset == null || "".equals(reqChartset)) {
			reqChartset = "UTF-8";
		}
		StringEntity myEntity = new StringEntity(content, reqChartset);
		httpPost.addHeader("Content-Type", contentType);
		httpPost.setEntity(myEntity);
		CloseableHttpResponse response1 = null;
		try {
			response1 = httpclient.execute(httpPost);
			HttpEntity entity1 = response1.getEntity();
			if (respChartset == null || "".equals(respChartset)) {
				respChartset = "UTF-8";
			}
			StatusLine statusLine = response1.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode != 200) {
				logger.error("current request url error,satusCode:{},ReasonPhrase:{}", statusCode,
						statusLine.getReasonPhrase());
				throw new IOException("request url statusCode is 500!");
			}
			String responseBody = EntityUtils.toString(entity1, respChartset);
			EntityUtils.consume(entity1);
			return responseBody;
		} finally {
			if (response1 != null){
				response1.close();
			}

		}
	}

	/**
	 * 通过POST方式获取指定地址的内容
	 * 
	 * @param strUrl
	 *            URL地址
	 * @param json
	 *            传送数据
	 * @return
	 */
	public static String getConnectionJson(String strUrl, String json) {
		// 存放响应结果
		String sTotalString = "";
		HttpURLConnection httpConn = null;
		OutputStream os = null;
		OutputStreamWriter osw = null;
		try {
			URL url = new URL(strUrl);

			/**
			 * 设置代理
			 * Proxy proxy = new Proxy(Proxy.Type.HTTP,new
			 * InetSocketAddress("10.1.27.102", 8080));
			 */
			httpConn = (HttpURLConnection) url.openConnection();
			/**
			 *  支持跳转
			 */
			HttpURLConnection.setFollowRedirects(true);
			httpConn.setDoOutput(true);
			/**
			 * /POST不使用缓存
			 */
			httpConn.setUseCaches(false);
			httpConn.setRequestMethod("POST");
			httpConn.setRequestProperty("Content-Type", "application/json");
			// 设置连接超时时间
			httpConn.setConnectTimeout(30000);
			// 设置读取超时时间
			httpConn.setReadTimeout(30000);
			httpConn.connect();
			os = httpConn.getOutputStream();
			osw = new OutputStreamWriter(os);
			osw.write(json.toCharArray(), 0, json.length());
			osw.flush();

			// 读取响应数据
			int code = httpConn.getResponseCode();

			if (httpConn.getConnectTimeout() == 0) {
				System.out.println("请求超时!");
				sTotalString = "请求超时!";
			}
			// 是否正常响应
			if (code == 200) {
				String sCurrentLine = "";

				// 读取响应数据
				InputStream is = httpConn.getInputStream();

				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				while ((sCurrentLine = reader.readLine()) != null) {
					if (sCurrentLine.length() > 0) {
						sTotalString = sTotalString + sCurrentLine.trim();
					}
				}
				is.close();
				System.out.println("返回数据:" + sTotalString);
				return sTotalString;
			} else {
				sTotalString = "远程服务器连接失败,错误代码:" + code;
			}
		} catch (Exception e) {
			sTotalString = "远程服务器异常:" + e.getMessage();
		} finally {
			if (osw != null && os != null && httpConn != null) {
				try {
					// 关闭流
					osw.close();
					os.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
				// 断开连接
				httpConn.disconnect();
			}
		}
		return sTotalString;
	}
}
