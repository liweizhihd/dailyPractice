package com.prac.demo1.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @Auther: liweizhi
 * @Date: 2018/12/17 15:17
 * @Description:
 */
@Component
public class TestAsync {

    @Async("scheduleTaskExecutor")
    public Future<String> test01(){
        System.out.println("test01() starting...");
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test01() end...");
        return new AsyncResult<>("test01");
    }

    @Async("scheduleTaskExecutor")
    public Future<String> test02(){
        System.out.println("test02() starting...");
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test02() end...");
        return new AsyncResult<>("test02");
    }


}
