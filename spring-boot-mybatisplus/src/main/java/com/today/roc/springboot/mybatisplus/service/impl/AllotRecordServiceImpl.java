package com.today.roc.springboot.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.today.roc.springboot.mybatisplus.entity.BaseEntity;
import com.today.roc.springboot.mybatisplus.form.AllotCollectBean;
import com.today.roc.springboot.mybatisplus.form.AllotSearchWrapBean;
import com.today.roc.springboot.mybatisplus.service.AllotRecordService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年06月18日 00:09*
 * log.info()
 */
@Service
public class AllotRecordServiceImpl implements AllotRecordService {


    public void pageSkuNotAllot(Long tenantId, Long userId, Integer isAllot) {
        AllotSearchWrapBean searchWrapBean = new AllotSearchWrapBean();
        AllotCollectBean collectBean = new AllotCollectBean();
        searchWrapBean.setIsAllot(isAllot);
        searchWrapBean.setSkuType(0);
        searchWrapBean.setSearchType(0);
        //1.判断是否需要查询各个表并校验参数
        //获取查询结果，放入AllotCollectWrapBean
        //根据查询结果获取查询条件，放入AllotSearchWrapBean，供最后查询类使用
        if (!searchAllotRecord(collectBean,searchWrapBean)){

        }
        //2.最后查询（notAllot是sku表，allocated 是allot表）
        //通过AllotSearchWrapBean进行分页查询，统一构建最终查询条件
        LambdaQueryWrapper<BaseEntity> queryWrapper = buildFinalSearchWrapper(searchWrapBean, BaseEntity.class);
        //分页查询，获取结果
        //3.各自封装结果进行处理，传入AllotCollectWrapBean
        wrapResultForSku(collectBean);

    }

    private void wrapResultForSku(AllotCollectBean collectWrapBean) {


    }

    private void wrapResultForComposeSku(AllotCollectBean collectWrapBean) {


    }

    private <T extends BaseEntity> LambdaQueryWrapper<T> buildFinalSearchWrapper(AllotSearchWrapBean bean, Class<T> zlass) {
        LambdaQueryWrapper<T> queryWrapper;
        if (0 == bean.getSearchType()) {
            queryWrapper= Wrappers.lambdaQuery(zlass);
            //sku查询
        }else if (1 == bean.getSearchType()) {
            queryWrapper= Wrappers.lambdaQuery(zlass);
            //allot查询
        }else {
            return null;
        }
        return queryWrapper;
    }

    public Boolean searchAllotRecord(AllotCollectBean collectBean,AllotSearchWrapBean searchWrapBean) {
        boolean searchNotAllot = searchWrapBean.isSearchNotAllot();
        List<Long> inSkuIdList = searchWrapBean.getInSkuIdList();
        List<Long> notInSkuIdList = searchWrapBean.getNotInSkuIdList();
        List<Long> allotSkuIdList = collectBean.getAllotSkuIdList();
        List<Long> skuIdListFromComposeSku = collectBean.getSkuIdListFromComposeSku();

        

        if (searchNotAllot) {
            //查未分配的
            if (CollectionUtils.isNotEmpty(skuIdListFromComposeSku)) {
                //过滤为否的情况下分配记录还有的 剩下就是sku要查的 in
                inSkuIdList = skuIdListFromComposeSku.stream().filter(v -> !allotSkuIdList.contains(v)).collect(Collectors.toList());
            } else {
                //allotSkuIdList：分配记录中条件查询后已分配的
                //有值 sku not in  没有值表示都未分配
                notInSkuIdList = allotSkuIdList;
            }
        } else {
            //是/全部
            if (CollectionUtils.isNotEmpty(skuIdListFromComposeSku) && CollectionUtils.isEmpty(allotSkuIdList)) {
                inSkuIdList = skuIdListFromComposeSku;
            } else {
                inSkuIdList = allotSkuIdList;
            }
        }
        searchWrapBean.setInSkuIdList(inSkuIdList).setNotInSkuIdList(notInSkuIdList);
        searchFromSku(collectBean,searchWrapBean);
        return Boolean.TRUE;
    }

    private void searchFromSku(AllotCollectBean collectBean,AllotSearchWrapBean bean) {
    }
}
