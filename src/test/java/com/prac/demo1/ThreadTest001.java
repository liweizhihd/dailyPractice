package com.prac.demo1;

import lombok.extern.slf4j.Slf4j;
import others.concurrent.VolatileThread001;

/**
 * @auther: liweizhi
 * Date: 2019/2/28 16:22
 * Description:
 */
@Slf4j
public class ThreadTest001 {

    public static void main(String[] args) throws InterruptedException {
        VolatileThread001 volatileThread001 = new VolatileThread001();
        volatileThread001.start();
        Thread.sleep(100L);
        System.out.println("set false");
        volatileThread001.setFlag(false);
    }

    static class MyThreadVolatile extends Thread {
        volatile private boolean flag = true;

        @Override
        public void run() {
            super.run();
            log.info("enter MyThreadVolatile");
            while (flag) {

            }
            log.info("leave MyThreadVolatile");
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }
    }

    private static class SharedVariableThread extends Thread {
        private int count = 5;

        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count--;
            System.out.println("由 " + SharedVariableThread.currentThread().getName() + " 计算，count=" + count);
        }
    }
}

