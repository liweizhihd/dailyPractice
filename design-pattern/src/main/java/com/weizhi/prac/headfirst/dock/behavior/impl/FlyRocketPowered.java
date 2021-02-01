package com.weizhi.prac.headfirst.dock.behavior.impl;


import com.weizhi.prac.headfirst.dock.behavior.Flyable;

/**
 * @Auther: liweizhi
 * @Date: 2018/12/14 17:02
 * @Description:
 */
public class FlyRocketPowered implements Flyable {
    @Override
    public void fly() {
        System.out.println("I'm flying with a rocket!");
    }
}
