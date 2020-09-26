package com.demo.design.observer;

/**
 * 观察者类
 *
 * @author jj
 * @since 2020/8/23 21:31
 */
public interface Observer {
    String getName();

    String setName(String name);

    /**
     * 支援盟友方法
     */
    void help();

    /**
     * 遭受攻击方法
     *
     * @param acc
     */
    void beAttacked(AllyControlCenter acc);
}
