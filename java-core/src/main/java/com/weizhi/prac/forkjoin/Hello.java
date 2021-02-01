package com.weizhi.prac.forkjoin;

import java.util.concurrent.ForkJoinPool;

/**
 * @author liweizhi
 * @date 2020/12/31
 */
public class Hello {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(new ForkJoinPool().getPoolSize());
    }
}
