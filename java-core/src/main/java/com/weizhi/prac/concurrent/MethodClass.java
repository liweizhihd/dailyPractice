package com.weizhi.prac.concurrent;

import lombok.extern.slf4j.Slf4j;

/**
 * @auther: liweizhi
 * Date: 2019/3/4 15:24
 * Description:
 */
@Slf4j
public class MethodClass {

    public static void main(String[] args) throws Exception {
        lock003();
    }

    public static void lock003(){
        final Lock003rw service = new Lock003rw(false);
        Runnable read = () -> {
            service.read();
        };
        Runnable write = new Runnable() {
            @Override
            public void run() {
                service.write();
            }
        };
        Thread[] arr = new Thread[5];
        for (int i = 0; i < 5; i++) {
            arr[i] = new Thread(read);
        }
        for (int i = 0; i < 5; i++) {
            arr[i].start();
            if (i == 1){
                new Thread(write).start();
            }
        }
    }

    public static void volatile002() throws InterruptedException {
        VolatileThread002[] mythreadArray = new VolatileThread002[100];
        for (int i = 0; i < 100; i++) {
            mythreadArray[i] = new VolatileThread002();
        }
        for (int i = 0; i < 100; i++) {
            if (i == 50){
                Thread.sleep(1000L);
                log.info("sleep");
            }
            mythreadArray[i].start();
        }

    }

    public static void volatile001() throws InterruptedException {
        VolatileThread001 volatileThread001 = new VolatileThread001();
        volatileThread001.start();
        Thread.sleep(100L);
        System.out.println("set false");
        volatileThread001.setFlag(false);
    }

}
