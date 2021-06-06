package com.today.roc.springboot.mybatisplus.config;

import com.alibaba.ttl.threadpool.TtlExecutors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @name: TaskExecutorPool
 * @description: 线程池的配置类
 * @type: JAVA
 * @since: 2020/6/16 14:25
 * @author: slien.zuo
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(GoodsExecutorConfig.class)
public class TaskExecutorPool {

    @Autowired
    public GoodsExecutorConfig goodsExecutorConfig;

    @Bean(name = "goodsAsyncThreadPool")
    public Executor getAsyncExecutor() {
        log.info("===goodsExecutor_thread-pool_init_start:" + goodsExecutorConfig.toString());
        ThreadPoolTaskExecutor goodsExecutor = new ThreadPoolTaskExecutor();
        goodsExecutor.setCorePoolSize(goodsExecutorConfig.getCorePoolSize());
        goodsExecutor.setMaxPoolSize(goodsExecutorConfig.getMaxPoolSize());
        goodsExecutor.setQueueCapacity(goodsExecutorConfig.getQueueCapacity());
        goodsExecutor.setKeepAliveSeconds(goodsExecutorConfig.getKeepAliveSeconds());
        goodsExecutor.setThreadNamePrefix(goodsExecutorConfig.getThreadNamePrefix());

        // 线程优化策略，当前不添加线程池策略
        /*
            AbortPolicy:该策略是线程池的默认策略。使用该策略时，如果线程池队列满了丢掉这个任务并且抛出RejectedExecutionException异常
            CallerRunsPolicy:使用此策略，如果添加到线程池失败，那么主线程会自己去执行该任务，不会等待线程池中的线程去执行。就像是个急脾气的人，我等不到别人来做这件事就干脆自己干
            DiscardOldestPolicy:这个策略从字面上也很好理解，丢弃最老的。也就是说如果队列满了，会将最早进入队列的任务删掉腾出空间，再尝试加入队列。
                                因为队列是队尾进，队头出，所以队头元素是最老的，因此每次都是移除对头元素后再尝试入队
            DiscardPolicy:这个策略和AbortPolicy的slient版本，如果线程池队列满了，会直接丢掉这个任务并且不会有任何异常
         */
        RejectedExecutionHandler executionHandler = null;
        if (!StringUtils.isEmpty(goodsExecutorConfig.getExecutionHandler())) {
            executionHandler = initExecutionHandler(goodsExecutorConfig.getExecutionHandler());
        }
        goodsExecutor.setTaskDecorator(new GoodsTaskDecorator());
        goodsExecutor.setRejectedExecutionHandler(executionHandler);
        goodsExecutor.initialize();
        log.info("===goodsExecutor_thread-pool_init_end");
        return TtlExecutors.getTtlExecutor(goodsExecutor);
    }

    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SalesAsyncExceptionHandler();
    }

    class SalesAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
            StringBuffer excpBuffer = new StringBuffer();
            excpBuffer.append("[Exception message] - ");
            excpBuffer.append(throwable.getMessage());
            excpBuffer.append("   [Method name] - ");
            excpBuffer.append(method.getName());
            if (objects != null && objects.length > 0) {
                for (Object param : objects) {
                    excpBuffer.append("  [Parameter value] - ");
                    excpBuffer.append(param);
                }
            }
            log.error(excpBuffer.toString());
        }
    }

    private RejectedExecutionHandler initExecutionHandler(String executionHandler) {
        if (executionHandler.equals(EXECUTION_HANDLER_AP)) {
            return new ThreadPoolExecutor.AbortPolicy();
        } else if (executionHandler.equals(EXECUTION_HANDLER_CRP)) {
            return new ThreadPoolExecutor.CallerRunsPolicy();
        } else if (executionHandler.equals(EXECUTION_HANDLER_DOP)) {
            return new ThreadPoolExecutor.DiscardOldestPolicy();
        } else if (executionHandler.equals(EXECUTION_HANDLER_DP)) {
            return new ThreadPoolExecutor.DiscardPolicy();
        }
        return null;
    }

    /// 线程池优化策略
    public static final String EXECUTION_HANDLER_AP  = "AbortPolicy";
    public static final String EXECUTION_HANDLER_CRP = "CallerRunsPolicy";
    public static final String EXECUTION_HANDLER_DOP = "DiscardOldestPolicy";
    public static final String EXECUTION_HANDLER_DP  = "DiscardPolicy";

}