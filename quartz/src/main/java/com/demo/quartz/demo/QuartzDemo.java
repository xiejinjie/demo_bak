package com.demo.quartz.demo;

import com.demo.quartz.job.MyJob;
import org.junit.Test;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

/**
 * @author jjxiek
 * @since 2020/1/2 14:02
 */

public class QuartzDemo {
    public static void main(String[] args){
        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            // and start it off
            System.out.println("=========启动==========");
            scheduler.start();

            // define the job and tie it to our HelloJob class
            System.out.println("=========创建任务==========");
            JobDetail job = newJob(MyJob.class)
                .withIdentity("job1", "group1")
                .build();
            // Trigger the job to run now, and then repeat every 40 seconds
            Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                    .withIntervalInSeconds(4)
                    .repeatForever())
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
//    @Test
//    public void fun1(){
//        SchedulerFactory schedFact = new StdSchedulerFactory();
//
//        Scheduler sched = schedFact.getScheduler();
//
//        sched.start();
//
//        // define the job and tie it to our HelloJob class
//        JobDetail job = newJob(HelloJob.class)
//            .withIdentity("myJob", "group1")
//            .build();
//
//        // Trigger the job to run now, and then every 40 seconds
//        Trigger trigger = newTrigger()
//            .withIdentity("myTrigger", "group1")
//            .startNow()
//            .withSchedule(simpleSchedule()
//                .withIntervalInSeconds(40)
//                .repeatForever())
//            .build();
//
//        // Tell quartz to schedule the job using our trigger
//        sched.scheduleJob(job, trigger);
//    }
    @Test
    public void fun2(){

    }

}
