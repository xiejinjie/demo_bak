package com.demo.quartz.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author jjxiek
 * @since 2020/1/6 19:17
 */
@Profile("schedule")
@Component
public class SpringScheduleTimer {
    private static final Logger logger = LoggerFactory.getLogger(SpringScheduleTimer.class);
    @Async
    @Scheduled(cron = "0/30 * * * * ?")
    public void fun1() throws InterruptedException {
        logger.info("任务1开始");
        Thread.sleep(5000);
        logger.info("任务1完成");
    }
    @Async
    @Scheduled(cron = "0/30 * * * * ?")
    public void fun2() throws InterruptedException {
        logger.info("任务2开始");
        Thread.sleep(5000);
        logger.info("任务2完成");
    }
}
