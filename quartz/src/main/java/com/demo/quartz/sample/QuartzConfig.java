package com.demo.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author jjxiek
 * @since 2020/1/3 10:06
 */
@Profile("sample")
@Configuration
public class QuartzConfig {

    // 使用jobDetail包装job
    @Bean
    public JobDetail myJobDetail(){
        return JobBuilder.newJob(MyJob.class).storeDurably().build();
    }

    // 把jobDetail注册到trigger上去
    @Bean
    public Trigger myJobTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(15).repeatForever();
        return TriggerBuilder.newTrigger()
            .forJob(myJobDetail())
            .withIdentity("myJobTrigger")
            .withSchedule(scheduleBuilder)
            .build();
    }

    // 使用jobDetail包装job
    @Bean
    public JobDetail myCronJobDetail() {
        return JobBuilder.newJob(MyCronJob.class).withIdentity("myCronJob").storeDurably().build();
    }

    // 把jobDetail注册到Cron表达式的trigger上去
    @Bean
    public Trigger CronJobTrigger() {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");
        return TriggerBuilder.newTrigger()
            .forJob(myCronJobDetail())
            .withIdentity("myCronJobTrigger")
            .withSchedule(cronScheduleBuilder)
            .build();
    }
}
