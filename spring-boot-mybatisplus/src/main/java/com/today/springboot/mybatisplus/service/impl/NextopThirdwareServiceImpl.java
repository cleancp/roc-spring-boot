package com.today.springboot.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.today.springboot.mybatisplus.common.DateUtil;
import com.today.springboot.mybatisplus.entity.NextopThirdware;
import com.today.springboot.mybatisplus.form.BinaryTupleForm;
import com.today.springboot.mybatisplus.mapper.NextopThirdwareMapper;
import com.today.springboot.mybatisplus.service.INextopThirdwareService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author roc.zou
 * @since 2021-05-22
 */
@Slf4j
@Service
public class NextopThirdwareServiceImpl extends ServiceImpl<NextopThirdwareMapper, NextopThirdware> implements INextopThirdwareService {

    @Override
    public void testSave(BinaryTupleForm<Long, String> form) {
        NextopThirdware thirdware = buildEntity(form);
        log.info("保存时间：" + DateUtil.getCurrentDateStr());
        this.save(thirdware);
        List<NextopThirdware> list = this.list(buildWrapper());
    }

    LambdaQueryWrapper<NextopThirdware> buildWrapper() {
        //SELECT  id,sku,update_id,update_time,create_id,create_time  FROM nextop_thirdware
        // WHERE ((id = ? AND sku = ?) OR (id = ? AND sku = ?) OR (id = ? AND sku = ?))
        LambdaQueryWrapper<NextopThirdware> queryWrapper = Wrappers.lambdaQuery(NextopThirdware.class);
        List<NextopThirdware> list = Lists.newArrayList();
        list.add(buildEntity(new BinaryTupleForm<Long, String>(1L, "1")));
        list.add(buildEntity(new BinaryTupleForm<Long, String>(2L, "2")));
        list.add(buildEntity(new BinaryTupleForm<Long, String>(3L, "3")));
        list.forEach(
                v -> {
                    queryWrapper.or(
                            i -> i.eq(NextopThirdware::getId, v.getId()).eq(NextopThirdware::getSku, v.getSku())
                    );
                }
        );
        return queryWrapper;
    }

    @Override
    public void testUpdate(BinaryTupleForm<Long, String> form) {
        NextopThirdware thirdware = buildEntity(form);
        log.info("修改时间：" + DateUtil.getCurrentDateStr());
        this.updateById(thirdware);
    }

    NextopThirdware buildEntity(BinaryTupleForm<Long, String> form) {
        NextopThirdware thirdware = new NextopThirdware();
        thirdware.setId(form.getK());
        thirdware.setSku(form.getV());
        thirdware.setCreateId(form.getK());
        thirdware.setUpdateId(form.getK());
        return thirdware;
    }

}
