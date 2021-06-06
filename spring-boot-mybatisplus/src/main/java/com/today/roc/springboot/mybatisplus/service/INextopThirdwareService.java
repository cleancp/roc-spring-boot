package com.today.roc.springboot.mybatisplus.service;

import com.today.roc.springboot.mybatisplus.entity.NextopThirdware;
import com.baomidou.mybatisplus.extension.service.IService;
import com.today.roc.springboot.mybatisplus.form.BinaryTupleForm;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author roc.zou
 * @since 2021-05-22
 */
public interface INextopThirdwareService extends IService<NextopThirdware> {


    void asyncExec(BinaryTupleForm<Long, String> form);

    void testSave(BinaryTupleForm<Long,String> form);

    void testUpdate(BinaryTupleForm<Long, String> form);
}
