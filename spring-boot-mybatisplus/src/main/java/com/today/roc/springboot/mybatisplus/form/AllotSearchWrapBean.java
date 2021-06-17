package com.today.roc.springboot.mybatisplus.form;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collections;
import java.util.List;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年06月18日 00:03*
 * log.info()
 */
@Data
@Accessors(chain = true)
public class AllotSearchWrapBean {

    //0 未分配 1 已分配
    private Integer searchType;

    //0 否 1 是
    private Integer isAllot;

    private boolean searchNotAllot;

    //0 普通 1 组合
    private Integer skuType;

    private Long tenantId;

    private Long userId;

    private List<Long> inSpuList = Collections.EMPTY_LIST;

    private List<Long> inSkuIdList = Collections.EMPTY_LIST;

    private List<Long> notInSkuIdList = Collections.EMPTY_LIST;


}
