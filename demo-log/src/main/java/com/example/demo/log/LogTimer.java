package com.example.demo.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author jj
 * @since 2020/3/2 14:46
 */
@Component
public class LogTimer {
    private static final Logger logger = LoggerFactory.getLogger(LogTimer.class);
    private static AtomicInteger i = new AtomicInteger();
    @Scheduled(cron = "1/10 * * * * ?")
    public void print(){
        logger.info("info" + i.getAndIncrement());
        logger.warn("warn" + i.getAndIncrement());
        logger.error("error" + i.getAndIncrement());
    }
}
