package com.demo.qywx.wx;

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
    public static final String WX_URL = "https://qyapi.weixin.qq.com";

    public static String CORP_ID;
    public static String CONTACT_SECRET;
    @Autowired Environment env;
    @PostConstruct
    public void init(){
        CORP_ID = env.getProperty("qywx.corpId");
        CONTACT_SECRET = env.getProperty("qywx.secret.contacts");
    }
}
