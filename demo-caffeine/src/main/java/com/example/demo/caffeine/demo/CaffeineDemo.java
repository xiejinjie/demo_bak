package com.example.demo.caffeine.demo;

import com.github.benmanes.caffeine.cache.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author jjxiek
 * @since 2020/1/9 15:10
 */
public class CaffeineDemo {
    /**
     * 同步手动装载
     */
    @Test
    public void fun1(){
        Cache<String, String> cache = Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .maximumSize(10_000)
            .build();
        String key = "text";
        String graph = "hello";
        // 插入条目
        cache.put(key, graph);
        // 查找条目，不存在返回null
        System.out.println(cache.getIfPresent("text"));
        // 删除缓存条目
        cache.invalidate(key);
        System.out.println(cache.getIfPresent("text"));
        // 查找条目，如果失效，自动装载
        System.out.println(cache.get("text", k -> "重新装载hello-" + k));;
    }

    /**
     * 同步自动装载
     */
    @Test
    public void fun2(){
        // 设置失效自动装载策略
        LoadingCache<String, String> cache = Caffeine.newBuilder()
            .maximumSize(10_000)
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .build(key ->  "重新装载hello-" + key);
        String key = "text";
        String graph = "hello";
        // 插入条目
        cache.put(key, graph);
        // Lookup and compute an entry if absent, or null if not computable
        System.out.println(cache.get(key));
        // Lookup and compute entries that are absent
        List<String> list = new ArrayList<>();
        list.add("text");
        Map<String, String> values = cache.getAll(list);
        System.out.println(values);

        cache.invalidate(key);
        System.out.println(cache.get(key));
    }
    /**
     * 异步装载
     */
//    public void fun3(){
//        AsyncLoadingCache<Object, Object> async =
//            Caffeine.newBuilder()
//                .expireAfterWrite(10, TimeUnit.SECONDS)
//                .maximumSize(10_000)
//                .buildAsync();
//    }
}
