package com.study.core;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version V1.0
 * @Description:
 * @author: h'mm
 * @date: 2020-10-18 15:45
 */
@RestController
public class IndexController {

    @GetMapping(name="/sourceA")
    public  String sourceA(){
        return "访问资源A";
    }
}
