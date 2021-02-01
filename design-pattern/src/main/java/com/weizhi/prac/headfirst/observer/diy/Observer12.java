package com.weizhi.prac.headfirst.observer.diy;

import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: liweizhi
 * @Date: 2018/12/24 17:57
 * @Description:
 */
@Slf4j
public class Observer12 implements Observer, Displayable {
    private Subject subject;
    private Float temperature;
    private Float humidity;
    private Float pressure;

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    @Override
    public void display() {
        log.info("temperature:{}",temperature);
    }

    public Observer12(Subject subject) {
        this.subject = subject;
        subject.registerObserver(this);
    }
}
