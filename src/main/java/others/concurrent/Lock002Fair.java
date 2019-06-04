package others.concurrent;

import java.time.Instant;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @auther: liweizhi
 * Date: 2019/3/5 10:06
 * Description:
 */
public class Lock002Fair {
    public static void main(String[] args) throws InterruptedException {
        final Service service = new Service(false);
        //true为公平锁，false为非公平锁
        Runnable runnable = () -> {
            System.out.println("★线程" + Thread.currentThread().getName() + "运行了 at " + Instant.now().toEpochMilli());
            service.serviceMethod();
        };
        Thread[] threadArray = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threadArray[i] = new Thread(runnable);
            threadArray[i].setName("T-" + i);
        }
        for (int i = 0; i < 10; i++) {
            threadArray[i].start();
        }
    }

    static public class Service {
        private ReentrantLock lock;
        private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();


        public Service(boolean isFair) {
            super();
            lock = new ReentrantLock(isFair);
        }

        public void serviceMethod() {
            lock.lock();
            try {
                System.out.println("ThreadName=" + Thread.currentThread().getName() + "获得锁定 at " + Instant.now().toEpochMilli());
            } finally {
                lock.unlock();
            }
        }
    }

}
