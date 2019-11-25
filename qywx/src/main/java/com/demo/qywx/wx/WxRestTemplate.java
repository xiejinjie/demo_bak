package com.demo.qywx.wx;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriTemplateHandler;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.Map;

/**
 * 微信请求工具类
 */
@Component
public class WxRestTemplate {
    private RestTemplate restTemplate;
    private UriTemplateHandler uriTemplateHandler;

    @PostConstruct
    public void init(){
        restTemplate = new RestTemplate();
        uriTemplateHandler = restTemplate.getUriTemplateHandler();

    }

    public <T extends  WxResponse> T getForObject(WxApi api, Class<T> clazz){
        return restTemplate.getForObject(WxContent.WX_URL + api.getPath(), clazz);
    }
    public <T extends  WxResponse> T getForObject(WxApi api, Class<T> clazz, Map<String, ?> uriVariables){
        String url = WxContent.WX_URL + api.getPath() + "?corpid=1";
        URI build = UriComponentsBuilder.fromHttpUrl(url).buildAndExpand(uriVariables).toUri()
                ;

        return restTemplate.getForObject(WxContent.WX_URL + api.getPath(), clazz, uriVariables);
    }

}
