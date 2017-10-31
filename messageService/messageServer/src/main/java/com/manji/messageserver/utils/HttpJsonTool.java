package com.manji.messageserver.utils;

import com.google.gson.JsonObject;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import java.io.*;
import java.util.UUID;

/**
 * Created by pudding on 2016-12-13.
 */
public class HttpJsonTool {
    private static final Logger logger = LoggerFactory.getLogger(HttpJsonTool.class);
    public static final int cache = 10 * 1024;

    private static final CloseableHttpClient httpClient;
    public static final String CHARSET = "UTF-8";

    static {
        RequestConfig config = RequestConfig.custom().setConnectTimeout(60000)
                .setSocketTimeout(30000).build();
        httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config)
                .build();
    }

    public static String sendHttpJsonPost(String url, JsonObject json) {
        String result = "";
        if (StringUtils.isBlank(url) && json != null) {
            return null;
        }
        HttpPost post = new HttpPost(url);
        try {
            StringEntity stringEntity = new StringEntity(json.toString());
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            post.setEntity(stringEntity);
            CloseableHttpResponse response = httpClient.execute(post);
            result = getHttpResult(response);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Https Request url={}  Error Message={}", url, e.getMessage());
        }
        return null;
    }
    /**
     * @param url
     * @param json
     * @param token 授权的票据
     * @return
     */
    public static String sendHttpJsonPost(String url, JsonObject json, String token) {
        String result = "";
        if (StringUtils.isBlank(url) && json != null && StringUtils.isNotEmpty(token)) {
            return null;
        }
        HttpPost httpPost = new HttpPost(url);

        try {
            StringEntity stringEntity = new StringEntity(json.toString());
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
//            httpPost.setHeader("Content-Type","application/json");
            httpPost.setHeader("Authorization", "Bearer " + token);
            httpPost.setEntity(stringEntity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            result = getHttpResult(response);
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Https Request url={}  Error Message={}", url, e.getMessage());
        }

        return null;

    }

    public static String sendHttpJsonPost(String url, String json, String token) {

        if (StringUtils.isBlank(url) && json != null && StringUtils.isNotEmpty(token)) {
            return null;
        }
        HttpPost httpPost = new HttpPost(url);
        try {
            StringEntity stringEntity = new StringEntity(json);
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setHeader("Authorization", "Bearer " + token);
            httpPost.setEntity(stringEntity);

            CloseableHttpResponse response = httpClient.execute(httpPost);
            return getHttpResult(response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Https Request url={}  Error Message={}", url, e.getMessage());
        }

        return null;

    }

    public static String sendHttpJsonPost(String url, String token) {
        if (StringUtils.isBlank(url) && StringUtils.isNotEmpty(token)) {
            return null;
        }
        HttpPost httpPost = new HttpPost(url);
        try {
            httpPost.setHeader("Authorization", "Bearer " + token);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            return getHttpResult(response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Https Request url={}  Error Message={}", url, e.getMessage());
        }
        return null;
    }

    /**
     * delete Reqiest
     *
     * @param url
     * @param token
     * @return
     */
    public static String sendHttpJsonDelete(String url, String token) {
        if (StringUtils.isBlank(url) && StringUtils.isNotEmpty(token)) {
            return null;
        }
        try {
            HttpDelete httpDelete = new HttpDelete(url);
            httpDelete.setHeader("Authorization", "Bearer " + token);
            CloseableHttpResponse response = httpClient.execute(httpDelete);
            return getHttpResult(response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Https Request url={}  Error Message={}", url, e.getMessage());
        }
        return null;
    }
    /**
     * restful GET
     *
     * @param url
     * @return
     */
    public static String sendHttpJsonGet(String url, String token) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Authorization", "Bearer " + token);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            return getHttpResult(response);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Https Request url={}  Error Message={}", url, e.getMessage());
        }
        return null;
    }
    /**
     * put json Request
     *
     * @param url
     * @param json
     * @param token
     * @return
     */
    public static String sendHttpJsonPut(String url, JsonObject json, String token) {

        if (StringUtils.isBlank(url) && json != null && StringUtils.isNotEmpty(token)) {
            return null;
        }
        HttpPut httpPut = new HttpPut(url);
        try {
            StringEntity stringEntity = new StringEntity(json.toString(), "utf-8");
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPut.setHeader("Authorization", "Bearer " + token);
            httpPut.setEntity(stringEntity);

            CloseableHttpResponse response = httpClient.execute(httpPut);
            return getHttpResult(response);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Https Request url={}  Error Message={}", url, e.getMessage());
        }
        return null;
    }
    private static String getHttpResult(CloseableHttpResponse response) throws IOException {
        HttpEntity entity=null;
        try {
            if (response.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
                entity = response.getEntity();
                if (entity != null) {
                    return EntityUtils.toString(entity, "utf-8");
                }
            }
        }finally {
            EntityUtils.consume(entity);
            response.close();
        }
        return null;
    }
    /**
     * 下载历史文件
     * @param url
     * @param
     */
    public static void downLoadHistoryFile(String url, String path,String name){
        File file=null;
        File file1=null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            String fileName = getFileName(response);
            HttpEntity entity = response.getEntity();
            inputStream = entity.getContent();
            //如果文件路径不存在，则创建文件路径，以免报错
            file1 = new File(path);
            if(!file1.exists()){
                file1.mkdirs();
            }
            file= new File(path+"\\"+name+fileName.substring(fileName.indexOf(".")));
            fileOutputStream=new FileOutputStream(file);
            bufferedOutputStream=new BufferedOutputStream(fileOutputStream);
            byte[] buffer=new byte[cache];
            int ch = 0;

            while ((ch = inputStream.read(buffer)) != -1) {
                bufferedOutputStream.write(buffer,0,ch);
            }
            bufferedOutputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
            logger.error("文件下载失败 errorMessage={}",e.getMessage());

        }finally {
            try {
                inputStream.close();
                bufferedOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.info("文件下载成功 fileName={}",file.getAbsolutePath());
    }
    /**
     * 下载文件
     *
     * @param url  下载的url
     * @param path 文件保存路径
     */
    public static void downLoadFile(String url, String path) {
        InputStream inputStream = null;
        FileOutputStream fileOutputStream;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            inputStream = entity.getContent();
            File file1 = new File(path);
            if(!file1.exists()){
                file1.mkdirs();
            }
            File file = new File(getFilePath(path,url));

            fileOutputStream=new FileOutputStream(file);
            bufferedOutputStream=new BufferedOutputStream(fileOutputStream);
            byte[] buffer=new byte[cache];
            int ch = 0;
            while ((ch = inputStream.read(buffer)) != -1) {
                bufferedOutputStream.write(buffer,0,ch);
            }
            bufferedOutputStream.flush();

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("文件下载失败 errorMessage={}",e.getMessage());
        }finally {
            try {
                inputStream.close();
                bufferedOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 获取response要下载的文件的默认路径
     * @param
     * @return
     */
    public static String getFilePath(String path,String url){
       // System.out.println("URL:"+url.split("/")[7]);
        String fileName = url.split("/")[7].split("\\?")[0];
        //System.out.println(fileName);
        String splash = "/";
        String filepath = path + splash + fileName;
        System.out.println(filepath);
        return filepath;
    }
    public static void main(String[] args)throws Exception{
        downLoadHistoryFile("https://a1.easemob.com/1177161212178229/mjms/chatfiles/538871c4-c5cc-11e6-b50d-b77b0293fb9a","D:/history/file",UUID.randomUUID().toString());
    }
    private static String getFileName(CloseableHttpResponse response){
        String fileName = null;
        Header header = response.getFirstHeader("Content-Disposition");
        //开始解析文件头信息，这里使用的是HeaderElement对象作为文件头的基础信息
        HeaderElement[] elements = header.getElements();
        for (HeaderElement el : elements) {
            //遍历，获取filename。filename信息对应的就是下载文件的文件名称。
            NameValuePair pair = el.getParameterByName("filename");
            System.out.println(pair.getName() + ":" + pair.getValue());
            fileName = pair.getValue();
            try {
                fileName = new String(fileName.getBytes("ISO8859-1"), "utf-8");
                //System.out.println("转码后："+fileName);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }
}
