package com.study.json;

import com.alibaba.fastjson.JSON;
import com.study.http.Event;
import com.study.utils.JsonUtils;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

/**
 * @version V1.0
 * @Description:
 * @author: h'mm
 * @date: 2020-10-12 18:13
 */
public class JsonListMap {

    /**
     * get 请求获取json
     * @return
     */
    public static String doGetJson() {
        String result = null;
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet("http://v.juhe.cn/todayOnhistory/queryEvent.php?key=ae6a6073b397d06325b0b8b267e4213d&date=1/1");
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                //System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
                result = EntityUtils.toString(responseEntity);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        try {
            // 释放资源
            httpClient.close();
            if (response != null) {
                response.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }

    /**
     * POST---有参测试(对象参数)
     */
    @Test
    public String doPostJson() {
        String result = null;
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 创建Post请求
        HttpPost httpPost = new HttpPost("http://localhost:8080/demoServlet/myServlet.action");
        Map<String,Object> map = new HashMap<>();
        map.put("name", "潘晓婷");
        map.put("age", 18);
        map.put("gender", "女");
        map.put("motto", "姿势要优雅~");
        // 我这里利用阿里的fastjson，将Object转换为json字符串;
        // (需要导入com.alibaba.fastjson.JSON包)
        String jsonString = JSON.toJSONString(map);

        StringEntity entity = new StringEntity(jsonString, "UTF-8");

        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);

        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
                result = EntityUtils.toString(responseEntity);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

            try {
                // 释放资源
                httpClient.close();
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        return result;

    }

    @Test
    public void jsonList() {
        String json = doGetJson();
        Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
        classMap.put("result", Event.class);

        Map<String,Object> map = JsonUtils.toObject(json, Map.class, classMap);

        List<Event> list = (List<Event>) map.get("result");
        System.out.println(JsonUtils.getObjectString(map));

        for (Event event : list) {
            System.out.println(event);
        }
    }

    @Test
    public void jsonMap() {

        Map<String, Object> map = new HashMap<>(5);
        map.put("name", "潘晓婷");
        map.put("age", 18);
        Map<String, String> titleMap = new HashMap<>(5);
        titleMap.put("fame", "大球碰小球");
        titleMap.put("skill", "九球天后");
        map.put("titleMap", titleMap);
        String json = JsonUtils.getObjectString(map);
        System.out.println(json);


        Map<String, Class<?>> classMap = new HashMap<String, Class<?>>(5);
        classMap.put("titleMap", Map.class);
        Map<String, Object> requestMap = JsonUtils.toObject(json, Map.class, classMap);
        Set<String> strings = requestMap.keySet();
        for (String key : strings) {
            System.out.println(key + " :" + map.get(key));
        }
    }

}
