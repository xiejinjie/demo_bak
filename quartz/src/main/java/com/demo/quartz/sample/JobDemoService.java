package com.demo.quartz.sample;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class JobDemoService implements Job {

    public JobDemoService() {
    }
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.err.println("list=============================");
    }
}
