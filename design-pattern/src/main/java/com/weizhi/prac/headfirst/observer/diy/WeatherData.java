package com.weizhi.prac.headfirst.observer.diy;

import java.util.ArrayList;

/**
 * @Auther: liweizhi
 * @Date: 2018/12/24 17:15
 * @Description:
 */
public class WeatherData implements Subject {

    private ArrayList<Observer> observers;
    private Float temperature;
    private Float humidity;
    private Float pressure;

    public WeatherData() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        int index = observers.indexOf(observer);
        if (index > 0) {
            observers.remove(index);
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers
        ) {
            observer.update(temperature, humidity, pressure);
        }
    }

    public void setMeasurements (float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public void measurementsChanged () {
        notifyObservers();
    }

}
