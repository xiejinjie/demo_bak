package com.demo.springboot.condition;

import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jj
 * @since 2020/10/5 16:55
 */
//@ConditionalOnProperty
//@ConditionalOnBean
//@ConditionalOnClass
//@ConditionalOnResource
//@ConditionalOnExpression
//@ConditionalOnWebApplication
@Configuration
public class ConditionConfigure {

    @Bean
    public TestConditionBean testConditionBean() {
        return new TestConditionBean();
    }

    static class TestConditionBean {
        public void print() {
            System.out.println("ok");
        }
    }
}
