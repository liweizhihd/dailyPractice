package head.first.designmode.dock.behavior.impl;

import head.first.designmode.dock.behavior.Flyable;

/**
 * @Auther: liweizhi
 * @Date: 2018/12/14 16:45
 * @Description:
 */
public class FlyNoWay implements Flyable {
    @Override
    public void fly() {
        System.out.println("i cant fly");
    }
}
