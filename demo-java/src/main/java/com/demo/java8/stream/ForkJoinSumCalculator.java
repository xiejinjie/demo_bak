package com.demo.java8.stream;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;
/**
 * RecursiveTask：可以提交到ForkJoinPool的任务
 *
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    public static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();

    public static final long THRESHOLD = 10_000;
    // 不再将任务分 解为子任务的 数组大小
    private final long[] numbers;
    // 子任务处理起始和终止位置
    private final int start;
    private final int end;

    // 公共构造 函数用于 创建主任 务
    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }
    // 私有构造函数用于以递 归方式为主任务创建子任务
    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        // 求和部分
        int length = end - start;
        // 小于阈值排序
        if (length <= THRESHOLD) {
            return computeSequentially();
        }
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length/2);
        leftTask.fork();
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length/2, end);
        Long rightResult = rightTask.compute();
        Long leftResult = leftTask.join();
        return leftResult + rightResult;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return FORK_JOIN_POOL.invoke(task);
    }
    
    public static void main(String[] args) {
        long l = forkJoinSum(10_000_000);
        System.out.println(l);
    }
}