package com.prac.demo1.schedule;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Auther: liweizhi
 * @Date: 2019/1/15 19:42
 * @Description:
 */
@Component
public class DemoSchedule {

    @Async
    @Scheduled(cron = "0 */10 * * * ?")
    public void demoTask(){
        System.out.println("hahaha:" + LocalDateTime.now());
    }

}
