package com.weizhi.prac.headfirst.dock.behavior.impl;


import com.weizhi.prac.headfirst.dock.behavior.Quackable;

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
