package com.tectonic.common.core.config;

import com.tectonic.common.core.utils.SpringUtils;
import com.tectonic.common.core.utils.Threads;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.VirtualThreadTaskExecutor;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置
 *
 * @author xiaoyan.song
 **/
@Slf4j
@AutoConfiguration
public class ThreadPoolConfig {

    /**
     * 核心线程数 = cpu 核心数 + 1
     */
    private final int core = Runtime.getRuntime().availableProcessors() + 1;

    private ScheduledExecutorService scheduledExecutorService;

    /**
     * 执行周期性或定时任务
     */
    @Bean(name = "scheduledExecutorService")
    protected ScheduledExecutorService scheduledExecutorService() {
        // daemon 必须为 true
        BasicThreadFactory.Builder builder = new BasicThreadFactory.Builder().daemon(true);
        if (SpringUtils.isVirtual()) {
            builder.namingPattern("virtual-schedule-pool-%d").wrappedFactory(new VirtualThreadTaskExecutor().getVirtualThreadFactory());
        } else {
            builder.namingPattern("schedule-pool-%d");
        }
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(core,
            builder.build(),
            new ThreadPoolExecutor.CallerRunsPolicy()) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                Threads.printException(r, t);
            }
        };
        this.scheduledExecutorService = scheduledThreadPoolExecutor;
        return scheduledThreadPoolExecutor;
    }

    /**
     * 销毁事件
     */
    @PreDestroy
    public void destroy() {
        try {
            log.info("====关闭后台任务任务线程池====");
            Threads.shutdownAndAwaitTermination(scheduledExecutorService);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
