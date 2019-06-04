package head.first.designmode.dock;

import head.first.designmode.dock.behavior.impl.FlyNoWay;
import head.first.designmode.dock.behavior.impl.Quack;

/**
 * @Auther: liweizhi
 * @Date: 2018/12/14 17:00
 * @Description:
 */
public class ModelDuck extends Duck {
    @Override
    public void display() {
        System.out.println("i'm model duck");
    }

    public ModelDuck() {
        super();
        super.flyable = new FlyNoWay();
        super.quackable = new Quack();
    }
}
