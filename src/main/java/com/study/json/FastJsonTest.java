package com.study.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @version V1.0
 * @Description:
 * @author: h'mm
 * @date: 2020-10-12 14:15
 */
public class FastJsonTest {

    @Test
    public void testFastJson() {
        //map转为json字符串
        Map<String, Object> map = new HashMap<>();
        map.put("name", "潘晓婷");
        map.put("age", 18);
        Object toJSON = JSON.toJSON(map);
        System.out.println(toJSON);

        //json字符串转为map对象
        Map<String, Object> mapObject = JSON.parseObject(toJSON.toString(), new TypeReference<Map<String, Object>>() {
        });
        Set<String> strings = mapObject.keySet();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            System.out.println(key + " :"+map.get(key));
        }
    }


}
