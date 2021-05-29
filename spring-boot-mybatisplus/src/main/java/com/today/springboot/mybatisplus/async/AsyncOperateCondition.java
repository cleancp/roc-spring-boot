package com.today.springboot.mybatisplus.async;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description：操作人处理条件对象
 * author：roc.zou
 * 2021/5/24 2:05 下午
 */
@Data
@NoArgsConstructor
public class AsyncOperateCondition {

    /**
     * 当前操作人id
     */
    private Long operatorId;
    /**
     * 是否跳过拦截：默认true，针对特殊的业务场景（异步、定时任务、消息中间件等），需要开发人员手动设置为false
     */
    private Boolean skip = true;
}
