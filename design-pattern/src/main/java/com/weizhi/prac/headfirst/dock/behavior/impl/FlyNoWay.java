package com.weizhi.prac.headfirst.dock.behavior.impl;


import com.weizhi.prac.headfirst.dock.behavior.Flyable;

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
