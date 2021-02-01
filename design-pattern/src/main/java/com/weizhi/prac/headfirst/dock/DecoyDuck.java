package com.weizhi.prac.headfirst.dock;


import com.weizhi.prac.headfirst.dock.behavior.impl.FlyNoWay;
import com.weizhi.prac.headfirst.dock.behavior.impl.MuteQuack;

/**
 * @Auther: liweizhi
 * @Date: 2018/12/14 16:52
 * @Description:
 */
public class DecoyDuck extends Duck {
    @Override
    public void display() {
        System.out.println("i'm decoy");
    }

    public DecoyDuck() {
        super();
        flyable = new FlyNoWay();
        quackable = new MuteQuack();
    }
}
