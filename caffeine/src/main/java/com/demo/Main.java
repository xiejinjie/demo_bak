package com.demo;

import org.springframework.boot.autoconfigure.cache.CacheProperties;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args){
        LoadingCache<Key, Graph> graphs = CacheProperties.Caffeine.newBuilder()
                .maximumSize(10_000)
                .expireAfterWrite(5, TimeUnit.SECONDS)
                .refreshAfterWrite(1, TimeUnit.SECONDS)
                .build(key -> createExpensiveGraph(key));
    }
}
