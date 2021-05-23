package com.today.springboot.mybatisplus.controller;


import com.today.springboot.mybatisplus.form.BinaryTupleForm;
import com.today.springboot.mybatisplus.form.SingleForm;
import com.today.springboot.mybatisplus.service.INextopThirdwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author roc.zou
 * @since 2021-05-22
 */
@RestController
@RequestMapping("/thirdware")
public class NextopThirdwareController {

    @Autowired
    INextopThirdwareService iNextopThirdwareService;

    @PostMapping("save")
    public String save(@RequestBody BinaryTupleForm<Long, String> form) {
        iNextopThirdwareService.testSave(form);
        return "";
    }

    @PostMapping("update")
    public String update(@RequestBody BinaryTupleForm<Long, String> form) {
        iNextopThirdwareService.testUpdate(form);
        return "";
    }

}
