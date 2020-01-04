package com.demo.qywx.obs.service;


import com.example.demo.bean.User;
import com.example.demo.dao.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ServiceDemo implements IServiceDemo{
    @Resource
    UserDao dao;
    @Override
    public List<User> listUser() {
        return dao.listUser();
    }
}
