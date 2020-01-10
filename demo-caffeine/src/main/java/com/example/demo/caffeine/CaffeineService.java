package com.example.demo.caffeine;

import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author jjxiek
 * @since 2019/11/18 17:44
 */
public interface CaffeineService {
    @Cacheable(value="CACHES", sync=true)
    Person getPerson();
    @Cacheable(value="CACHES", sync=true)
    List<Person> listPerson();
    void test();
}
