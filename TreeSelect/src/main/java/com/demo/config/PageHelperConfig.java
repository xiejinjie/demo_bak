package com.demo.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jjxiek
 * @since 2019/12/6 14:21
 */
@Configuration
public class PageHelperConfig {
    @Bean
    public PageHelper createPageHelper(){
        PageHelper pageHelper = new PageHelper();
        return pageHelper;
    }

}
