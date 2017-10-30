package com.manji.finance.withdrawals.Pay;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.*;

public class PayUtil {
	// 商户密钥
	public static final String SECRET_KEY = "ACC8bh7D36241E3r";
	public static final String CHARSET = "GBK";

	public static final String URL = "http://121.15.180.72/CmbBank_B2B/UI/DIDI/DoBusiness.ashx";


	public static String post(String PKG) {
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost
		HttpPost httppost = new HttpPost(URL);
		// 创建参数队列
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("RequestData", PKG));
		UrlEncodedFormEntity uefEntity;

		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					return  EntityUtils.toString(entity, "UTF-8");
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}


	/**
	 * 对参数签名
	 */
	public static String sign(String reqDataJSON, String secretKey, String charset) {
		StringBuffer buffer = new StringBuffer();

		try {
			JSONObject json = new JSONObject(reqDataJSON);
			List<String> keyList = sortParams(json);
			for (String key : keyList) {
				buffer.append(key).append("=").append(json.get(key)).append("&");
			}
			buffer.append(secretKey);// 商户密钥
			System.out.println(buffer.toString());
			// 创建加密对象
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			// 传入要加密的字符串,按指定的字符集将字符串转换为字节流
			messageDigest.update(buffer.toString().getBytes(charset));
			byte byteBuffer[] = messageDigest.digest();

			// 將 byte转换为16进制string
			StringBuffer strHexString = new StringBuffer();
			for (int i = 0; i < byteBuffer.length; i++) {
				String hex = Integer.toHexString(0xff & byteBuffer[i]);
				if (hex.length() == 1) {
					strHexString.append('0');
				}
				strHexString.append(hex);
			}
			return strHexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "";
	}



	/**
	 * 对参数排序
	 */
	public static List<String> sortParams(JSONObject json) {
		List<String> list = new ArrayList<String>();
		Iterator it = json.keys();
		while (it.hasNext()) {
			list.add((String) it.next());
		}
		Collections.sort(list, new Comparator<String>() {
			public int compare(String o1, String o2) {
				String[] temp = { o1.toLowerCase(), o2.toLowerCase() };
				Arrays.sort(temp);
				if (o1.equalsIgnoreCase(temp[0])) {
					return -1;
				} else if (temp[0].equalsIgnoreCase(temp[1])) {
					return 0;
				} else {
					return 1;
				}
			}
		});
		return list;
	}


	public static String buildParam(Map<String, String> reqDataMap) {
		JSONObject jsonParam = new JSONObject();
		try {
			jsonParam.put("version", "1.0");
			jsonParam.put("charset", CHARSET);// 支持GBK和UTF-8两种编码
			jsonParam.put("sign", sign(reqDataMap, SECRET_KEY));
			jsonParam.put("signType", "SHA-256");
			jsonParam.put("reqData", reqDataMap);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonParam.toString();
	}

	/**
	 * 对参数签名：
	 * 对reqData所有请求参数按从a到z的字典顺序排列，如果首字母相同，按第二个字母排列，以此类推。排序完成后按将所有键值对以“&”符号拼接。
	 * 拼接完成后再加上商户密钥。示例：param1=value1&param2=value2&...&paramN=valueN&secretKey
	 *
	 * @param reqDataMap
	 *            请求参数
	 * @param secretKey
	 *            商户密钥
	 */
	public static String sign(Map<String, String> reqDataMap, String secretKey) {
		StringBuffer buffer = new StringBuffer();
		List<String> keyList = sortParams(reqDataMap);
		for (String key : keyList) {
			buffer.append(key).append("=").append(reqDataMap.get(key)).append("&");
		}
		buffer.append(secretKey);// 商户密钥
		System.out.println(buffer.toString());

		try {
			// 创建加密对象
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			// 传入要加密的字符串,按指定的字符集将字符串转换为字节流
			messageDigest.update(buffer.toString().getBytes(CHARSET));
			byte byteBuffer[] = messageDigest.digest();

			// 將 byte转换为16进制string
			StringBuffer strHexString = new StringBuffer();
			for (int i = 0; i < byteBuffer.length; i++) {
				String hex = Integer.toHexString(0xff & byteBuffer[i]);
				if (hex.length() == 1) {
					strHexString.append('0');
				}
				strHexString.append(hex);
			}
			return strHexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}


	/**
	 * 对参数按字典顺序排序，不区分大小写
	 */
	public static List<String> sortParams(Map<String, String> reqDataMap) {
		List<String> list = new ArrayList<String>(reqDataMap.keySet());
		Collections.sort(list, new Comparator<String>() {
			public int compare(String o1, String o2) {
				String[] temp = { o1.toLowerCase(), o2.toLowerCase() };
				Arrays.sort(temp);
				if (o1.equalsIgnoreCase(temp[0])) {
					return -1;
				} else if (temp[0].equalsIgnoreCase(temp[1])) {
					return 0;
				} else {
					return 1;
				}
			}
		});
		return list;
	}




	/**
	 * 解码
	 * @param str
	 * @return string
	 */
	public static byte[] decode(String str){
		byte[] bt = null;
		try {
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			bt = decoder.decodeBuffer( str );
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bt;
	}

	/**
	 * 二进制数据编码为BASE64字符串
	 *
	 * @param bytes
	 * @return
	 * @throws Exception
	 */
	public static String encode(final byte[] bytes) {
		return new String(Base64.encodeBase64(bytes));
	}



}
