package com.demo.springboot.test;

import com.demo.springboot.SpringBootApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * SpringBoot测试类
 * maven依赖
 *  org.springframework.boot:spring-boot-starter-test
 *  junit:junit
 *
 * @author jj
 * @since 2020/10/5 17:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootApp.class)
public class TestSpringBoot {
    @Autowired
    private TestSpringBootBean testBean;

    @Test
    public void fun() {
        testBean.print();
    }

}
