package com.weizhi.prac.headfirst.dock.behavior.impl;


import com.weizhi.prac.headfirst.dock.behavior.Quackable;

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
