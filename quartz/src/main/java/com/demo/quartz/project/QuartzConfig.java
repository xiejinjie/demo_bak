package com.demo.quartz.project;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Bean("syncJobDetail")
    public JobDetail syncJobDetail(){
        return newJob(SyncJob.class)
            .withIdentity("syncJob", "project")
            .storeDurably()
            .build();
    }
    /**
     * 使用spring持久化
     * @return
     */
    @Bean("syncTrigger")
    public Trigger syncTrigger(@Qualifier("syncJobDetail") JobDetail syncJobDetail){
        // 每天晚上两点执行数据同步任务
        Trigger trigger = newTrigger()
                .withIdentity("syncTrigger", "project")
                .withSchedule(cronSchedule("0/30 * * * * ?"))
                .forJob(syncJobDetail)
                .build();
        return trigger;
    }

    /**
     * 使用jdbc持久化
     * @param args
     */
    public static void main(String[] args) {
        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            // and start it off
            System.out.println("=========启动==========");
            scheduler.start();

            // define the job and tie it to our HelloJob class
            System.out.println("=========创建任务==========");
            JobDetail job = newJob(SyncJob.class)
                .withIdentity("job1", "jdbc")
                .build();
            // Trigger the job to run now, and then repeat every 40 seconds
            Trigger trigger = newTrigger()
                .withIdentity("trigger1", "jdbc")
                .startNow()
                .withSchedule(simpleSchedule()
                    .withIntervalInSeconds(4))
                .build();
            System.out.println("=========提交任务==========");
            scheduler.scheduleJob(job, trigger);

            Thread.sleep(20000);
            System.out.println("=========终止==========");
            scheduler.shutdown();
        } catch (SchedulerException | InterruptedException se) {
            se.printStackTrace();
        }
    }


}
