package com.demo.qywx.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author jjxiek
 * @since 2019/11/25 21:03
 */
@Component
public class WxContent {
    public static String CORP_ID;
    @Autowired Environment env;
    @PostConstruct
    public void init(){
        CORP_ID = env.getProperty("qywx.corpId");

    }
}
