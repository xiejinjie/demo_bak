package com.template.springboot;

import graphql.kickstart.execution.config.GraphQLSchemaProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author jj
 * @since 2020/9/23 23:13
 */
@Controller
public class TestController {
    @Autowired
    private GraphQLSchemaProvider provider;

    @GetMapping("hello")
    public String hello(){
        return "hello";
    }
}
