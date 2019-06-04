package head.first.designmode.observer.java;

import java.util.Observable;

/**
 * @Auther: liweizhi
 * @Date: 2018/12/24 17:15
 * @Description:
 */
public class WeatherData extends Observable {

    private Float temperature;
    private Float humidity;
    private Float pressure;


    public void setMeasurements (float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public void measurementsChanged () {
        //
        setChanged();
        // 不推送，让订阅者自己拉
        notifyObservers();
    }

    public Float getTemperature() {
        return temperature;
    }

    public Float getHumidity() {
        return humidity;
    }

    public Float getPressure() {
        return pressure;
    }
}
