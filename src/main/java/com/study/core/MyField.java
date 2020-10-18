package com.study.core;

import java.lang.annotation.*;

/**
 * @version V1.0
 * @Description: 自定义属性注解
 * @author: h'mm
 * @date: 2020-10-18 14:31
 */
//表明此注解会被javadoc等类似工具识别，并保留在API文档中
@Documented
//注解用到字段上
@Target(ElementType.FIELD)
//运行时使用注解
@Retention(RetentionPolicy.RUNTIME)
public @interface MyField {
    String description();
    int length();
}
