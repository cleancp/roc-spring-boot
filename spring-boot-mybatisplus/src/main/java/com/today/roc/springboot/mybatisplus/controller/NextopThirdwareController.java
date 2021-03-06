package com.today.roc.springboot.mybatisplus.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import com.today.roc.springboot.mybatisplus.async.AsyncOperateCondition;
import com.today.roc.springboot.mybatisplus.async.AsyncOperateContextHolder;
import com.today.roc.springboot.mybatisplus.form.BinaryTupleForm;
import com.today.roc.springboot.mybatisplus.service.INextopThirdwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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

    @PostMapping("async")
    public String async(@RequestBody BinaryTupleForm<Long, String> form) {
        //wrapCondition();
        iNextopThirdwareService.asyncExec(form);
        return "";
    }

    void wrapCondition() {
        AsyncOperateCondition condition = new AsyncOperateCondition();
        condition.setOperatorId(12345L);
        condition.setSkip(false);
        AsyncOperateContextHolder.setCondition(condition);
    }


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
