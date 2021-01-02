package com.demo.java8.stream;

import org.junit.Test;

import java.util.stream.LongStream;

/**
 * @author jj
 * @since 2020/12/29 13:44
 */

public class StreamDemo {
    @Test
    public void parallelTest() {
        // 并行流数量
        System.out.println("默认并发量为处理器核心数 : " + Runtime.getRuntime().availableProcessors());
        System.out.println("通过修改系统变量修改并发量 : " + 50);
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","50");
        long startTime=System.currentTimeMillis();   //获取开始时间
        LongStream.rangeClosed(1L, 100L)
                .parallel() // 并行
                .forEach(in->{
                    Thread thread = Thread.currentThread();
                    System.out.println("thread: "+thread.getName()+", value: "+in);
                    try{
                        Thread.sleep(500);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                });
        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
    }

}
