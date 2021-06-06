package com.today.roc.springboot.mybatisplus.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 线程池参数配置
 */
@Data
@ConfigurationProperties(prefix = GoodsExecutorConfig.THREADPOOL_PREFIX)
public class GoodsExecutorConfig {
    public static final String THREADPOOL_PREFIX = "thread.goods.pool";
    // 核心线程数
    private             int    corePoolSize;

    // 最大线程数
    private int maxPoolSize;

    // 队列最大长度
    private int queueCapacity;

    // 线程池维护线程所允许的空闲时间
    private int keepAliveSeconds;

    // 线程名称前缀：如 MyAsync-
    private String threadNamePrefix;

    // 线程池优化策略
    private String executionHandler;

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