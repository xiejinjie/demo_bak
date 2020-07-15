package com.example.demo.quartz.project;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jjxiek
 * @since 2020/1/3 17:47
 */
public class SyncJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(SyncJob.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("============定时同步开始============");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("============定时同步结束============");

    }
}
