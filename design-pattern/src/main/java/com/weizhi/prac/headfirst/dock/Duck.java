package com.weizhi.prac.headfirst.dock;


import com.weizhi.prac.headfirst.dock.behavior.Flyable;
import com.weizhi.prac.headfirst.dock.behavior.Quackable;

/**
 * @Auther: liweizhi
 * @Date: 2018/12/14 16:33
 * @Description:
 */
public abstract class Duck {
    Flyable flyable;
    Quackable quackable;

    public abstract void display();

    public void performFly() {
        flyable.fly();
    }

    public void performQuack() {
        quackable.quack();
    }

    public void swim() {
        System.out.println("All docks float,even decoys");
    }

    public Duck() {
        super();
    }

    public void setFlyable(Flyable flyable) {
        this.flyable = flyable;
    }

    public void setQuackable(Quackable quackable) {
        this.quackable = quackable;
    }
}
