package com.example.demo.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * OOM 测试
 * jvm参数 -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * @author jj
 * @since 2020/3/10 13:58
 */
public class HeapOOM {
    static class OOMObeject {

    }
    public static void main(String[] args){
        List<OOMObeject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObeject());
        }
    }
}
