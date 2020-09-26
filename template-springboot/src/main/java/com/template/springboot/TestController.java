package com.template.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author jj
 * @since 2020/9/23 23:13
 */
@Controller
public class TestController {

    @GetMapping("hello")
    public String hello(){
        return "hello";
    }
}
