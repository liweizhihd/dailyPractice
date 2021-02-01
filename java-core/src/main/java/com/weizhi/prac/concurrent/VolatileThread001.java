package com.weizhi.prac.concurrent;

import lombok.extern.slf4j.Slf4j;

/**
 * @auther: liweizhi
 * Date: 2019/3/4 15:19
 * Description:
 */
@Slf4j
public class VolatileThread001 extends Thread {
    //version 1
    //volatile private boolean flag = true;

    // version 2
    volatile private boolean flag = true;

    @Override
    public void run() {
        super.run();
        log.info("enter VolatileThread001");
        while (flag) {
            // version 1 do nothing

            // version 2
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("leave VolatileThread001");
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
