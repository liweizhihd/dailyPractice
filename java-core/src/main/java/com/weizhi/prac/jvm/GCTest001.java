package com.weizhi.prac.jvm;

/**
 * @auther: liweizhi
 * Date: 2019/3/26 09:32
 * Description:
 */
public class GCTest001 {
    public static void main(String[] args) {
        byte[] allocation1, allocation2;
        allocation1 = new byte[29273 * 1024];
        allocation2 = new byte[1000*1024];
    }
}
