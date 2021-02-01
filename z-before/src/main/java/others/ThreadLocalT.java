package others;/**
 * @Auther: liweizhi
 * @Date: 2019/1/29 14:28
 * @Description:
 */

import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @Author liweizhi
 * Date 2019/1/29 下午2:28
 */
public class ThreadLocalT {


    private static ThreadLocal<String> userId = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        test02();
    }

    public static void test02() throws InterruptedException {
        userId.set("id in main thread");
        Thread thread2 = new Thread(() -> {
            System.out.println("thread2 init:"+userId.get());
            userId.set("thread2");
            System.out.println("thread2 last:"+userId.get());
        });
        Thread thread1 = new Thread(() -> {
            System.out.println("thread1 init:"+userId.get());
            userId.set("thread1");
            System.out.println("thread1 last:"+userId.get());
            thread2.start();
            try {
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
        thread1.join();
        System.out.println("main:"+userId.get());
    }

    public static void test01() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                // 线程1两秒之后获得userid，并且设置userid为id1
                TimeUnit.SECONDS.sleep(2);
                System.out.println("initial userId in thread1:" + userId.get());
                userId.set("id1");
                System.out.println("thread1 set userId id1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                // 线程二获取初始的userId，然后一秒之后设置为id2，再过两秒之后再次读取userid
                System.out.println("initial userId in thread2:" + userId.get());
                TimeUnit.SECONDS.sleep(1);
                userId.set("id2");
                System.out.println("thread2 set userId id2");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("now userId in thread2:" + userId.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
        thread2.start();

        // 在main线程等待两个线程执行结束
        thread1.join();
        //System.out.println("=====");
        thread2.join();

        System.out.println("main method get userId:" + userId.get());
        userId.set("mainId");
        System.out.println("last userId:" + userId.get());
    }
}
