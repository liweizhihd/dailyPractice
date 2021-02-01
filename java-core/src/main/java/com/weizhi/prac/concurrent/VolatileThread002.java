package com.weizhi.prac.concurrent;

import lombok.extern.slf4j.Slf4j;

/**
 * @auther: liweizhi
 * Date: 2019/3/4 15:19
 * Description:
 */
@Slf4j
public class VolatileThread002 extends Thread {

    volatile public static int count;

    private static void addCount() {
        for (int i = 0; i < 100; i++) {
            count=i;
            /*try {
                Thread.sleep(200L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
        log.info("count={}", count);
    }

    @Override
    public void run() {
        addCount();
    }

}
