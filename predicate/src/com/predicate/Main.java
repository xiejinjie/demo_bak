package com.predicate;

import java.util.function.Predicate;

/**
 * 谓词使用
 * @author jjxiek
 * @since 2019/11/12 19:41
 */
public class Main {
    public static void main(String[] args){
        Predicate<String> predicate1 = (v1) -> {
            //此处别的逻辑处理，做简单的语法介绍就不详细写了。
            return v1.equals("1");
        };
        Predicate<String> predicate2 = (v1) -> {
            //此处别的逻辑处理，做简单的语法介绍就不详细写了。
            return v1.equals("2");
        };
        Predicate<String> predicate3 = predicate1.or(predicate2);

        //        Predicate<String> predicates = (v1) -> v1.equals("2"); 直接返回结果可这样写
        System.out.println(predicate1.test("1"));//true
        System.out.println(predicate1.test("2"));//false
        System.out.println(predicate3.test("1"));//true
        System.out.println(predicate3.test("2"));//true

    }


}
