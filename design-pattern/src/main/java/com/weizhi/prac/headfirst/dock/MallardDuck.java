package com.weizhi.prac.headfirst.dock;

import com.weizhi.prac.headfirst.dock.behavior.impl.FlyWithWing;
import com.weizhi.prac.headfirst.dock.behavior.impl.Quack;

/**
 * @Auther: liweizhi
 * @Date: 2018/12/14 16:51
 * @Description:
 */
public class MallardDuck extends Duck {
    @Override
    public void display() {
        System.out.println("i'm mallard");
    }

    public MallardDuck() {
        super();
        flyable = new FlyWithWing();
        quackable = new Quack();
    }
}
