package com.demo.qywx.basic;

import com.demo.qywx.wx.WxApi;
import com.demo.qywx.wx.WxContent;
import com.demo.qywx.wx.WxRestTemplate;
import com.demo.qywx.wx.response.TokenResponse;
import com.sun.jndi.toolkit.url.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("wx")
public class WxController {
    @Autowired
    WxRestTemplate wxRestTemplate;

    @GetMapping("token")
    public String getToken(){
        Map<String, String > param = new HashMap<>(2);
        param.put("corpid", WxContent.CORP_ID);
        param.put("corpsecret", WxContent.CONTACT_SECRET);
//        URI uri = new URI();

        TokenResponse response = wxRestTemplate.getForObject(WxApi.GET_TOKEN, TokenResponse.class, param);
        System.out.println(response);
        return response.getAccessToken();
    }
}
