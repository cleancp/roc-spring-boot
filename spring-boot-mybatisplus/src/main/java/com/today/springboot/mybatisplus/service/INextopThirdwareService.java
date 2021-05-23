package com.today.springboot.mybatisplus.service;

import com.today.springboot.mybatisplus.entity.NextopThirdware;
import com.baomidou.mybatisplus.extension.service.IService;
import com.today.springboot.mybatisplus.form.BinaryTupleForm;
import com.today.springboot.mybatisplus.form.SingleForm;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author roc.zou
 * @since 2021-05-22
 */
public interface INextopThirdwareService extends IService<NextopThirdware> {


    void testSave(BinaryTupleForm<Long,String> form);

    void testUpdate(BinaryTupleForm<Long, String> form);
}
