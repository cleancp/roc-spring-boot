package com.today.roc.springboot.base.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月01日 11:22*
 * log.info()
 */
@RestController
public class NotFoundController {

    @RequestMapping("/404.do")
    public String notFound() {
        //TODO 跳转到静态页面
        return "静态页面";
    }

}
