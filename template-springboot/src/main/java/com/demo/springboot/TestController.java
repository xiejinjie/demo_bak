package com.demo.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jj
 * @since 2020/9/23 23:13
 */
@Controller
public class TestController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello";
    }
}
