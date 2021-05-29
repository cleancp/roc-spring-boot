package com.today.springboot.mybatisplus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @name: GoodsExecutorConfig
 * @description: 线程池参数的配置
 * @type: JAVA
 * @since: 2020/6/16 14:24
 * @author: slien.zuo
 */
@Configuration
public class GoodsExecutorConfig {

    // 核心线程数
    @Value("${thread.goods.pool.corePoolSize}")
    private int corePoolSize;

    // 最大线程数
    @Value("${thread.goods.pool.maxPoolSize}")
    private int maxPoolSize;

    // 队列最大长度
    @Value("${thread.goods.pool.queueCapacity}")
    private int queueCapacity;

    // 线程池维护线程所允许的空闲时间
    @Value("${thread.goods.pool.keepAliveSeconds}")
    private int keepAliveSeconds;

    // 线程名称前缀：如 MyAsync-
    @Value("${thread.goods.pool.threadNamePrefix}")
    private String threadNamePrefix;

    // 线程池优化策略
    @Value("${thread.goods.pool.executionHandler}")
    private String executionHandler;

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public int getQueueCapacity() {
        return queueCapacity;
    }

    public void setQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }

    public int getKeepAliveSeconds() {
        return keepAliveSeconds;
    }

    public void setKeepAliveSeconds(int keepAliveSeconds) {
        this.keepAliveSeconds = keepAliveSeconds;
    }

    public String getThreadNamePrefix() {
        return threadNamePrefix;
    }

    public void setThreadNamePrefix(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
    }

    public String getExecutionHandler() {
        return executionHandler;
    }

    public void setExecutionHandler(String executionHandler) {
        this.executionHandler = executionHandler;
    }

    @Override
    public String toString() {
        return "TaskExecutorConfig{" +
                "corePoolSize=" + corePoolSize +
                ", maxPoolSize=" + maxPoolSize +
                ", queueCapacity=" + queueCapacity +
                ", keepAliveSeconds=" + keepAliveSeconds +
                ", threadNamePrefix='" + threadNamePrefix + '\'' +
                ", executionHandler='" + executionHandler + '\'' +
                '}';
    }
}