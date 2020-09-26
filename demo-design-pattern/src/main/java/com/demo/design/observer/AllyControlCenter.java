package com.demo.design.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 战略控制中心
 *
 * @author jj
 * @since 2020/8/23 21:28
 */
public abstract class AllyControlCenter {
    protected String allayName;

    protected List<Observer> players = new ArrayList<>();

    public String getAllayName() {
        return allayName;
    }

    public void setAllayName(String allayName) {
        this.allayName = allayName;
    }

    public void join(Observer obs) {
        System.out.println(obs.getName() + "加入" + this.getAllayName() + "战队");
        players.add(obs);
    }

    public void quit(Observer obs) {
        System.out.println(obs.getName() + "退出" + this.getAllayName() + "战队");
        players.remove(obs);
    }

    /**
     * 通知观察者
     */
    public abstract void notifyObserver(String name);
}
