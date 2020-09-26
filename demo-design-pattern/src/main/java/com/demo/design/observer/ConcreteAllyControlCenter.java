package com.demo.design.observer;

/**
 * 战队实现类
 *
 * @author jj
 * @since 2020/8/23 21:31
 */
public class ConcreteAllyControlCenter extends AllyControlCenter {
    public ConcreteAllyControlCenter(String allayName) {
        System.out.println(allayName + "战队组建成功");
        System.out.println("----------");
        this.allayName = allayName;
    }

    @Override
    public void notifyObserver(String name) {
        System.out.println(allayName + "战队紧急通知！盟友" + name + "遭受敌人攻击");
        for (Observer player : players) {
            if (!player.getName().equalsIgnoreCase(name)) {
                player.help();
            }
        }

    }
}
