package com.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jjxiek
 * @since 2019/11/18 15:43
 */
@Service
public class CaffeineServiceImpl implements CaffeineService {
    @Autowired
    private CacheManager cacheManager;
    @Override
    public Person getPerson(){
        System.out.println("1");
        Person p = new Person();
        p.setName("xjj");
        return p;
    }
    @Override
    public List<Person> listPerson(){
        System.out.println("2");
        List<Person> list = new ArrayList<>();
        Person p = new Person();
        p.setName("xxx");
        list.add(p);
        return list;
    }
    @Override
    public void test(){
        Cache cache = cacheManager.getCache("test");
        Person p = cache.get("p", Person.class);
        if (p == null){
            p = getPerson();
            cache.put("p", p);
        }
        System.out.println(p);
    }

}
