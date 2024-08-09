package com.jingli.admin.config;

import cn.hutool.core.thread.RejectPolicy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @Description 线程池参数设置，@EnableAsync 开启异步， @Async 标记
 * @author Zfenor
 */
@Slf4j
@EnableAsync
@Configuration
public class ThreadPoolConfig {

    /**
     * 核心线程数
     */
    private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2 + 1;
    /**
     * 最大线程数
     */
    private static final int MAX_POOL_SIZE = CORE_POOL_SIZE * 2;
    /**
     * 缓存等待列队大小
     */
    private static final int QUEUE_CAPACITY = 100;
    /**
     * 空闲线程存活时间
     */
    private static final int KEEP_ALIVE_TIME = 10;
    /**
     * 线程前缀
     */
    private static final String THREAD_NAME_PREFIX = "ASYN-TASKS-";
    /**
     * 线程组名称
     */
    private static final String THREAD_GROUP_NAME = THREAD_NAME_PREFIX;


    private static final ThreadPoolTaskExecutor EXECUTOR = new ThreadPoolTaskExecutor();

    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        log.debug("线程池参数设定");
        log.debug("CORE:{}, MAX:{}, QUEUE:{}, KEEP_ALIVE:{}, PREFIX:{}, GROUP:{}",
                CORE_POOL_SIZE, MAX_POOL_SIZE, QUEUE_CAPACITY, KEEP_ALIVE_TIME,
                THREAD_NAME_PREFIX, THREAD_GROUP_NAME);

        EXECUTOR.setCorePoolSize(CORE_POOL_SIZE);
        EXECUTOR.setMaxPoolSize(MAX_POOL_SIZE);
        EXECUTOR.setQueueCapacity(QUEUE_CAPACITY);
        EXECUTOR.setKeepAliveSeconds(KEEP_ALIVE_TIME);
        EXECUTOR.setThreadNamePrefix(THREAD_NAME_PREFIX);
        EXECUTOR.setThreadGroupName(THREAD_GROUP_NAME);

        //线程拒绝策略设置为 主线程运行
        EXECUTOR.setRejectedExecutionHandler(RejectPolicy.CALLER_RUNS.getValue());

        // 核心线程 可以超时关闭
        EXECUTOR.setAllowCoreThreadTimeOut(true);
        EXECUTOR.initialize();
        log.info("Executor init success");
        return EXECUTOR;
    }

}
