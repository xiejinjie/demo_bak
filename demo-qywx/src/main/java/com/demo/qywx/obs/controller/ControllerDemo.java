package com.demo.qywx.obs.controller;

import com.example.demo.bean.User;
import com.example.demo.service.IServiceDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ControllerDemo {
    @Autowired
    IServiceDemo serviceDemo;
    @RequestMapping("/hello")
    public String hello(){
        List<User> users = serviceDemo.listUser();
        System.out.print(users);
        return "success";
    }
}
