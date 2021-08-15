package com.changgou.seckill.config;

import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

public class AsyncConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {

        //定义线程池
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //核心线程数
        taskExecutor.setCorePoolSize(20);// 默认是8个。
        //线程池最大线程数
        taskExecutor.setMaxPoolSize(40); //设置线程池最大线程数  如果超过次数，则拒绝执行。该值可以根据业务自行设置
        //线程队列最大线程数
        taskExecutor.setQueueCapacity(10);//线程队列最大线程数

        //初始化
        taskExecutor.initialize();

        return taskExecutor;
    }
}
