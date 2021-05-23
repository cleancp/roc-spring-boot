package com.today.roc.springboot.base.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月01日 10:39*
 * log.info()
 */
@Api(tags = "hello2")
@RestController
@RequestMapping("/hello2")
public class Hello2Controller {

    private final Logger logger = LoggerFactory.getLogger(Hello2Controller.class);

    @ApiOperation(value = "hello")
    @PostMapping("/")
    public String hello(){
        logger.debug("输出日志信息");
        return "hello world";
    }
    @ApiOperation(value = "error")
    @GetMapping("/error")
    public String error(){
        int  a = 1/0;
        return "error";
    }

}
