package head.first.designmode.observer.diy;

/**
 * @Auther: liweizhi
 * @Date: 2018/12/24 17:12
 * @Description:
 */
public interface Observer {

    /**
     *
     * @param temperature 温度
     * @param humidity 湿度
     * @param pressure 气压
     */
    void update(float temperature, float humidity, float pressure);

}
