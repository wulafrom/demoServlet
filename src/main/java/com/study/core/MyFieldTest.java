package com.study.core;

import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @version V1.0
 * @Description: 测试注解
 * @author: h'mm
 * @date: 2020-10-18 15:04
 */
public class MyFieldTest {

    /**
     * 使用我们的自定义注解
     */
    @MyField(description = "用户名", length = 12)
    private String username;

    @Test
    public void testMyField() {

        // 获取类模板
        Class c = MyFieldTest.class;

        // 获取所有字段
        for (Field f : c.getDeclaredFields()) {
            // 判断这个字段是否有MyField注解
            if (f.isAnnotationPresent(MyField.class)) {
                MyField myField = f.getAnnotation(MyField.class);
                System.out.println("字段[" + f.getName() + "],描述" + myField.description() + "长度:[" + myField.length());
            }
        }

    }
}
