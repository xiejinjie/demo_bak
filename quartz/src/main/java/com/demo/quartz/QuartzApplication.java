package com.demo.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author jjxiek
 * @since 2020/1/3 10:25
 */
@EnableScheduling
@SpringBootApplication
public class QuartzApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(QuartzApplication.class);
    }
    public static void main(String[] args){
        SpringApplication.run(QuartzApplication.class, args);
    }
}
