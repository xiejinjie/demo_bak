package com.demo.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

/**
 * @author jjxiek
 * @since 2020/1/2 14:36
 */
public class MyCronJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("任务执行了" + new Date());
        // indexController.testMail();
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        String decode = URLDecoder.decode("https://hscrm.obs.cn-south-1.myhuaweicloud.com/hscrm/urltest/a%2Bb.jpg", "utf-8");
        System.out.println(decode);
    }
}

