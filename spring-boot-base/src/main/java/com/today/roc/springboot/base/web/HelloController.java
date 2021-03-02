package com.today.roc.springboot.base.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月01日 10:39*
 * log.info()
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/")
    public String hello(){
        logger.debug("输出日志信息");
        return "hello world";
    }

    @RequestMapping("/error")
    public String error(){
        int  a = 1/0;
        return "error";
    }

}
