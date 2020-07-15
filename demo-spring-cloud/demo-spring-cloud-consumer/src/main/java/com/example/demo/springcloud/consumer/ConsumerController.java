package com.example.demo.springcloud.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author jj
 * @since 2020/3/27 08:37
 */
@RestController
public class ConsumerController {
    @Autowired
    private RestTemplate template;
    @GetMapping("")
    public String index(){
        return template.getForObject("http://peer/",String.class);
    }
}
