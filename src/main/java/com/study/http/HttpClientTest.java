package com.study.http;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @version V1.0
 * @Description:
 * @author: h'mm
 * @date: 2020-10-11 14:57
 */
public class HttpClientTest {
    private final static Logger logger = LoggerFactory.getLogger(HttpUrlConnectionTest.class);

    @Test
    public void testHttpClient() {
        String path = "https://img1.qunarzz.com/travel/d6/1701/c3/ad1a5ce13c3e2bb5.jpg?ver=1";
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(path);
        try {
            CloseableHttpResponse response = closeableHttpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                InputStream inputStream = response.getEntity().getContent();
                FileOutputStream fos = new FileOutputStream("D:\\test3\\httpclient.jpg");
                int n = 0;
                byte[] bytes = new byte[1024];
                while ((n = inputStream.read(bytes)) != -1) {
                    fos.write(bytes, 0, n);
                }
                fos.flush();
                fos.close();
            }

        } catch (IOException e) {
            logger.error("创建请求对象失败，路径{}", path);
            e.printStackTrace();
        }
    }

    @Test
    public void testHttpClientParam() {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("param", "post"));
        params.add(new BasicNameValuePair("username", "admin"));
        params.add(new BasicNameValuePair("password", "password"));

        File file1 = new File("D:\\test3\\download.jpg");
        File file2 = new File("D:\\test3\\httpclient.jpg");
        HashMap<String, File> files = new HashMap<String, File>();
        files.put("file1", file1);
        files.put("file2", file2);
    }

    public static HttpEntity createHttpEntity(List<NameValuePair> params,HashMap<String, File> files){

        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        if (params!=null&&params.size()>0) {
            for (NameValuePair nameValuePair : params) {
                multipartEntityBuilder.addTextBody(nameValuePair.getName(),nameValuePair.getValue());
            }
        }
        if (files!=null&&files.size()>0) {
            Set<Map.Entry<String, File>> entries = files.entrySet();
            for (Map.Entry<String, File> entry : entries) {
                multipartEntityBuilder.addPart(entry.getKey(),new FileBody(entry.getValue()));
            }
        }

        return multipartEntityBuilder.build();
    }
}
