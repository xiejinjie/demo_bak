package com.demo.design.observer;

/**
 * @author jj
 * @since 2020/8/23 21:53
 */
public class Main {
    public static void main(String[] args) {
        AllyControlCenter acc = new ConcreteAllyControlCenter("金庸群侠");
        Observer
                player1 = new Player("杨过"),
                player2 = new Player("令狐冲"),
                player3 = new Player("张无忌"),
                player4 = new Player("段誉");
        acc.join(player1);
        acc.join(player2);
        acc.join(player3);
        acc.join(player4);

        player1.beAttacked(acc);
    }

}
