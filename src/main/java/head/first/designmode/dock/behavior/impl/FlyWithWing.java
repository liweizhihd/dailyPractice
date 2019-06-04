package head.first.designmode.dock.behavior.impl;

import head.first.designmode.dock.behavior.Flyable;

/**
 * @Auther: liweizhi
 * @Date: 2018/12/14 16:44
 * @Description:
 */
public class FlyWithWing implements Flyable {

    @Override
    public void fly() {
        System.out.println("i'm flying with wing");
    }
}
