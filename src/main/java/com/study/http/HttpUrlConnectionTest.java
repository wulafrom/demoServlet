package com.study.http;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @version V1.0
 * @Description:
 * @author: h'mm
 * @date: 2020-10-11 14:06
 */
public class HttpUrlConnectionTest {
    private final static Logger logger = LoggerFactory.getLogger(HttpUrlConnectionTest.class);

    public static void main(String[] args) {

        String path = "https://img1.qunarzz.com/travel/d6/1701/c3/ad1a5ce13c3e2bb5.jpg?ver=1";
        try {
            URL url = new URL(path);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                InputStream is = urlConnection.getInputStream();
                FileOutputStream fs = new FileOutputStream("D:\\test3\\download.jpg");
                byte[] bytes = new byte[1024];
                int n = 0;
                while ((n = is.read(bytes)) != -1) {
                    fs.write(bytes, 0, n);
                }
                fs.flush();
                fs.close();
                logger.info("问下下载成功，目标地址{}", path);
            }
        } catch (MalformedURLException e) {
            logger.error("建立url错误:" + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("建立连接错误:" + e.getMessage());
            e.printStackTrace();
        }
    }


}
