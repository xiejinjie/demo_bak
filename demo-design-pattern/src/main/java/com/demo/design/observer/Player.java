package com.demo.design.observer;

/**
 * @author jj
 * @since 2020/8/23 21:31
 */
public class Player implements Observer{
    private String name;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String setName(String name) {
        return name;
    }

    @Override
    public void help() {
        System.out.println("坚持住" + name + "来救你");
    }

    @Override
    public void beAttacked(AllyControlCenter acc) {
        // 通知盟友
        System.out.println(name + "被攻击");
        acc.notifyObserver(name);
    }
}
