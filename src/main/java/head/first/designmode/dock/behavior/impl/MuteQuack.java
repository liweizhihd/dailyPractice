package head.first.designmode.dock.behavior.impl;

import head.first.designmode.dock.behavior.Quackable;

/**
 * @Auther: liweizhi
 * @Date: 2018/12/14 16:47
 * @Description:
 */
public class MuteQuack implements Quackable {
    @Override
    public void quack() {
        System.out.println("<< silence >>");
    }
}
