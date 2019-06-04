package head.first.designmode.dock.behavior.impl;

import head.first.designmode.dock.behavior.Quackable;

/**
 * @Auther: liweizhi
 * @Date: 2018/12/14 16:46
 * @Description:
 */
public class Quack implements Quackable {
    @Override
    public void quack() {
        System.out.println("quack");
    }
}
