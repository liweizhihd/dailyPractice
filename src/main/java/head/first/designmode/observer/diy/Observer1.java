package head.first.designmode.observer.diy;

import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: liweizhi
 * @Date: 2018/12/24 17:57
 * @Description:
 */
@Slf4j
public class Observer1 implements Observer, Displayable {
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
        log.info("temperature:{},humidity:{},pressure:{}",temperature,humidity,pressure);
    }

    public Observer1(Subject subject) {
        this.subject = subject;
        subject.registerObserver(this);
    }

    public static void main(String[] args) {

        WeatherData subject = new WeatherData();
        Observer1 observer1 = new Observer1(subject);
        Observer12 observer12 = new Observer12(subject);

        subject.setMeasurements(1,2,3);
        subject.setMeasurements(2,2,3);
    }
}
