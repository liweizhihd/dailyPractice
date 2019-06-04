package head.first.designmode.observer.diy;

/**
 * @Auther: liweizhi
 * @Date: 2018/12/24 17:05
 * @Description:
 */
public interface Subject {

    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();

}
