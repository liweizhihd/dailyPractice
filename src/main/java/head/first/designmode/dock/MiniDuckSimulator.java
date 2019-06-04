package head.first.designmode.dock;

import head.first.designmode.dock.behavior.impl.FlyRocketPowered;

/**
 * @Auther: liweizhi
 * @Date: 2018/12/14 16:49
 * @Description:
 */
public class MiniDuckSimulator {
    public static void main(String[] args) {
        model();
    }

    public static void mallard(){
        Duck duck = new MallardDuck();
        duck.performFly();
        duck.performQuack();
        duck.display();
    }

    public static void model(){
        Duck model = new ModelDuck();
        model.performFly();
        model.setFlyable(new FlyRocketPowered());
        model.performFly();
    }
}
