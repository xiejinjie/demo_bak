package com.demo.quartz.project;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.*;

import static org.quartz.JobBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.CalendarIntervalScheduleBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.DateBuilder.*;

/**
 * @author jjxiek
 * @since 2020/1/3 16:37
 */
@Profile("project")
@Configuration
public class QuartzConfig {
    @Bean("syncTrigger")
    public Trigger syncTrigger(){
        // 每天晚上两点执行数据同步任务
        JobDetail jobDetail = newJob(SyncJob.class)
                .withIdentity("syncJob", "sync")
                .build();
        Trigger trigger = newTrigger()
                .withIdentity("syncTrigger", "sync")
                .forJob(jobDetail)
                .build();
        return trigger;
    }


}
