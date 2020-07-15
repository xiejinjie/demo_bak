package com.example.demo.springcloud.peer2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jj
 * @since 2020/3/27 08:15
 */
@EnableEurekaClient
@SpringBootApplication
@RestController
public class Peer2Application {
    public static void main(String[] args) {
        SpringApplication.run(Peer2Application.class, args);
    }
    @GetMapping("")
    public String index(){
        return "服务2应答";
    }
}
