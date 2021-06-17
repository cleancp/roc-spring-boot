package com.today.roc.springboot.mybatisplus.form;

import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年06月18日 00:06*
 * log.info()
 */
@Data
public class AllotCollectBean {

    //spuList

    //composeSkuList
    private List<Long> skuIdListFromComposeSku = Collections.EMPTY_LIST;

    //composeDetailSkuList

    //allotRecordList
    private List<Long> allotSkuIdList = Collections.EMPTY_LIST;

    //skuList

    //skuList

}
