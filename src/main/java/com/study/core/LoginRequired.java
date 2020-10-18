package com.study.core;

import java.lang.annotation.*;

/**
 * @version V1.0
 * @Description: 接口 登录拦截
 * @author: h'mm
 * @date: 2020-10-18 15:41
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {

}
