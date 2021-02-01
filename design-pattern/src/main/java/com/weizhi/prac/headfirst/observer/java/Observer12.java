package com.weizhi.prac.headfirst.observer.java;

import com.weizhi.prac.headfirst.observer.diy.Displayable;
import lombok.extern.slf4j.Slf4j;

import java.util.Observable;
import java.util.Observer;

/**
 * @Auther: liweizhi
 * @Date: 2018/12/24 17:57
 * @Description:
 */
@Slf4j
public class Observer12 implements Observer, Displayable {
    Observable observable;
    private Float temperature;

    @Override
    public void display() {
        log.info("temperature:{}", temperature);
    }


    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData) {
            WeatherData wd = (WeatherData) o;
            this.temperature = wd.getTemperature();
            display();
        }
    }

    public Observer12(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }
}
