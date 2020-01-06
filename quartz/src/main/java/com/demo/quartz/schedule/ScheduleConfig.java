package com.demo.quartz.schedule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author jjxiek
 * @since 2020/1/6 19:38
 */
@Profile("schedule")
@Configuration
public class ScheduleConfig {
    @Bean
    public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        //设置的线程数,可以根据需求调整
        taskScheduler.setPoolSize(5);
        return taskScheduler;
    }
}
