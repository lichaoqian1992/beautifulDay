package com.manji.circlemes.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.manji.circlemes.model.common.NetRes;

import net.sf.json.JSONObject;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SSLClient {

	private static PoolingHttpClientConnectionManager connMgr;

	private static RequestConfig requestConfig;

	private static final int MAX_TIMEOUT = 5000;

	static {
		// 设置连接池
		connMgr = new PoolingHttpClientConnectionManager();
		// 设置连接池大小
		connMgr.setMaxTotal(100);
		connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());

		RequestConfig.Builder configBuilder = RequestConfig.custom();
		// 设置连接超时
		configBuilder.setConnectTimeout(MAX_TIMEOUT);
		// 设置读取超时
		configBuilder.setSocketTimeout(MAX_TIMEOUT);
		// 设置从连接池获取连接实例的超时
		configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
		// 在提交请求之前 测试连接是否可用
		configBuilder.setStaleConnectionCheckEnabled(true);
		requestConfig = configBuilder.build();
	}

	public static String doPostSSL(String apiUrl, String paraJson) {

		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
				.setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
		HttpPost httpPost = new HttpPost(apiUrl);

		CloseableHttpResponse response = null;

		String httpStr = null;

		try {
			httpPost.setConfig(requestConfig);

			StringEntity stringEntity = new StringEntity(paraJson, "UTF-8");

			stringEntity.setContentEncoding("UTF-8");

			stringEntity.setContentType("application/json");

			httpPost.setEntity(stringEntity);

			response = httpClient.execute(httpPost);

			int statusCode = response.getStatusLine().getStatusCode();

			if (statusCode != HttpStatus.SC_OK) {

				return null;

			}

			HttpEntity entity = response.getEntity();

			if (entity == null) {

				return null;

			}

			httpStr = EntityUtils.toString(entity, "utf-8");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return httpStr;
	}

	private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
		SSLConnectionSocketFactory sslsf = null;
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {

				@Override
				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}

				@Override
				public void verify(String host, SSLSocket ssl) throws IOException {
				}

				@Override
				public void verify(String host, X509Certificate cert) throws SSLException {
				}

				@Override
				public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
				}
			});
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		return sslsf;
	}

	public static NetRes doNetPost(String apiUrl, String paraJson, Map<String, String> headers) {

		NetRes nr = new NetRes();

		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
				.setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();

		HttpPost httpPost = new HttpPost(apiUrl);

		CloseableHttpResponse response = null;

		String httpStr = "";

		try {

			httpPost.setConfig(requestConfig);

			if (headers != null && headers.size() > 0) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					httpPost.setHeader(entry.getKey(), entry.getValue());
				}
			}

			StringEntity stringEntity = new StringEntity(paraJson, "UTF-8");

			stringEntity.setContentEncoding("UTF-8");

			// stringEntity.setContentType("application/json");

			httpPost.setEntity(stringEntity);

			response = httpClient.execute(httpPost);

			int statusCode = response.getStatusLine().getStatusCode();

			nr.setCode(statusCode);

			HttpEntity entity = response.getEntity();

			if (entity == null) {
				nr.setContent("");
			} else {
				httpStr = EntityUtils.toString(entity, "utf-8");

				JSONObject obj = JSONObject.fromObject(httpStr);

				nr.setContent(obj);
				//

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return nr;
	}

	public static NetRes doNetGet(String apiUrl, Map<String, String> headers) {

		NetRes nr = new NetRes();

		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
				.setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();

		HttpGet httpGet = new HttpGet(apiUrl);
		CloseableHttpResponse response = null;

		String httpStr = "";

		try {

			httpGet.setConfig(requestConfig);

			if (headers != null && headers.size() > 0) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					httpGet.setHeader(entry.getKey(), entry.getValue());
				}
			}

			response = httpClient.execute(httpGet);

			int statusCode = response.getStatusLine().getStatusCode();

			nr.setCode(statusCode);

			HttpEntity entity = response.getEntity();

			if (entity == null) {

				nr.setContent("");

			} else {

				httpStr = EntityUtils.toString(entity, "utf-8");

				JSONObject obj = JSONObject.fromObject(httpStr);

				nr.setContent(obj);

			}

		} catch (ClientProtocolException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return nr;
	}

	public static NetRes doNetDelete(String apiUrl, Map<String, String> headers) {

		NetRes nr = new NetRes();

		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
				.setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();

		HttpDelete httpDelete = new HttpDelete(apiUrl);
		CloseableHttpResponse response = null;

		String httpStr = "";
		try {

			httpDelete.setConfig(requestConfig);

			if (headers != null && headers.size() > 0) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					httpDelete.setHeader(entry.getKey(), entry.getValue());
				}
			}

			response = httpClient.execute(httpDelete);

			int statusCode = response.getStatusLine().getStatusCode();

			nr.setCode(statusCode);

			HttpEntity entity = response.getEntity();

			if (entity == null) {
				nr.setContent("");
			} else {
				httpStr = EntityUtils.toString(entity, "utf-8");

				JSONObject obj = JSONObject.fromObject(httpStr);

				nr.setContent(obj);
				//

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return nr;
	}

	public static void main(String[] args) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("grant_type", "client_credentials");
		map.put("client_id", "YXA62mxDoMBKEeaRzKlcnOjZBg");
		map.put("client_secret", "YXA6iv4D821VmWu_Z2jGbp0tuVxO84s");
		String json = JSONObject.fromObject(map).toString();
		String s = doPostSSL("https://a1.easemob.com/1177161212178229/mjms/token", json);
		System.out.println(s);

	}

}
