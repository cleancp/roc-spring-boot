package com.today.springboot.mybatisplus.async;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * description：当前操作人Context
 * author：roc.zou
 * 2021/5/24 2:05 下午
 */
public class AsyncOperateContextHolder implements AutoCloseable {

    /**
     * 全局用户隔离的ThreadLocal
     * 用的是阿里的TransmittableThreadLocal可实现异步的ThreadLocal值传递
     */
    public static TransmittableThreadLocal<AsyncOperateCondition> currentUserContextHolder = new TransmittableThreadLocal<>();

    /**
     * @description: 获取操作人处理条件对象
     * @param: []
     * @return: com.nextop.config.async.AsyncOperateCondition
     * @author: roc.zou
     * @date: 2021/5/24 2:20 下午
     */
    public static AsyncOperateCondition getCondition() {
        return currentUserContextHolder.get();
    }

    /**
     * @description: 设置操作人处理条件
     * @param: [globalIsolationCondition]
     * @return: void
     * @author: roc.zou
     * @date: 2021/5/24 2:20 下午
     */
    public static void setCondition(AsyncOperateCondition globalIsolationCondition) {
        currentUserContextHolder.set(globalIsolationCondition);
    }

    public static void remove() {
        currentUserContextHolder.remove();
    }

    /**
     * @description: 跳过操作人处理
     * @param: []
     * @return: void
     * @author: roc.zou
     * @date: 2021/5/24 2:19 下午
     */
    public static void skip() {
        AsyncOperateCondition condition = getCondition();
        if (null != condition) {
            condition.setSkip(true);
        }
    }

    @Override
    public void close() throws Exception {
        remove();
    }

}
