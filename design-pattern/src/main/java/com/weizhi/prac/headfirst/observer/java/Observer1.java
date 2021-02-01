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
public class Observer1 implements Observer, Displayable {
    Observable observable;
    private Float temperature;
    private Float humidity;
    private Float pressure;

    @Override
    public void display() {
        log.info("temperature:{},humidity:{},pressure:{}", temperature, humidity, pressure);
    }


    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData) {
            WeatherData wd = (WeatherData) o;
            this.temperature = wd.getTemperature();
            this.humidity = wd.getHumidity();
            this.pressure = wd.getPressure();
            display();
        }
    }

    public Observer1(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    public static void main(String[] args) {
        WeatherData wd = new WeatherData();
        Observer1 observer1 = new Observer1(wd);
        Observer12 observer12 = new Observer12(wd);

        wd.setMeasurements(1, 2, 3);
        wd.setMeasurements(2, 2, 3);
    }
}
