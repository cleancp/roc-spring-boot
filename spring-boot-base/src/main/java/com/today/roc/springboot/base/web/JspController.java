package com.today.roc.springboot.base.web;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月01日 11:46*
 * log.info()
 */
@Controller
@RequestMapping("/jsp")
public class JspController {

    @RequestMapping("/hi")
    public String index() {
        return "index";
    }

}
