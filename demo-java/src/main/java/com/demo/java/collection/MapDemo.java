package com.demo.java.collection;

import org.junit.Test;

import java.util.HashMap;


/**
 * @author jj
 * @since 2020/12/16 08:34
 */

public class MapDemo {

    @Test
    public void hashMapTest(){
        /* 初始化 */
        //
        HashMap<String, String> map = new HashMap<>(2,0.75f);
        /* put过程 */
        String put = map.put("key", "value");
        /* get过程 */
        String value = map.get("key");
        System.out.println(value);
        /* hash过程 */
        int hashCode = "key".hashCode();
        int x = hashCode >>> 16;
        int result = hashCode ^ x;
        System.out.println(x);
    }
}
