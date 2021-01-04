package com.demo.java8.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author jj
 * @since 2021/1/2 10:03
 */
public class ForkJoinTest {

    @Test
    public void fun1() {
        int n = 100;
        List<Integer> ids = testList(n);
        ForkJoinTask task = new ForkJoinTask(ids);
        task.invoke();
        System.out.println(1);
        System.out.println(task.getResult());
    }
    private List<Integer> testList(int n) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            list.add(random.nextInt(100000));
        }
        return list;
    }
    static class ForkJoinTask extends RecursiveTask {
        static final ForkJoinPool forkJoinPool = new ForkJoinPool();
        private Integer[] ids;
        private String[] result;
        int index;

        public ForkJoinTask(List<Integer> ids) {
            this.ids = ids.toArray(new Integer[ids.size()]);
            this.result = new String[ids.size()];
            this.index = 0;
        }
        private ForkJoinTask(Integer[] ids, String[] result, int index) {
            this.ids = ids;
            this.result = result;
            this.index = index;
        }
        public List<String> getResult() {
            return Arrays.asList(result);
        }
        @Override
        protected Object compute() {
            if (index >= ids.length) {
                return null;
            }
            ForkJoinTask forkJoinTask = new ForkJoinTask(ids, result, index + 1);
            forkJoinTask.fork();
            try {
                // 请求接口
                Thread.sleep(200);
                result[index] = ids[index] + ": request success";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            forkJoinTask.join();
            return null;
        }
    }

}

