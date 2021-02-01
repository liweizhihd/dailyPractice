package com.prac.demo1;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Auther: liweizhi
 * @Date: 2019/1/14 15:14
 * @Description: 求一个大数的阶乘，分十个线程分别计算，拿到结果再相乘得到最终答案
 */
@Slf4j
public class FactorialCompute {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        int num = 1000000;

        // 计算阶乘
        BigDecimal ret = factorialCompute(num);
        System.out.println(ret);

        long end = System.currentTimeMillis();
        System.out.println("耗时:" + (end - start) + "ms");
    }

    public static BigDecimal factorialCompute(int num) throws ExecutionException, InterruptedException {

        //ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 200L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(10));
        // 创建一个固定的线程池，这个在项目中一般不显示创建，而是交给Spring管理
        ExecutorService executor = Executors.newFixedThreadPool(10);
        // 十个线程，也就是把大数分成十个区间
        int step = 10;
        int stepNum = num / 10;
        // 为了达到并行的效果，因为future.get()会阻塞线程
        CountDownLatch countDownLatch = new CountDownLatch(step);
        List<Future<BigDecimal>> futures = new ArrayList<>(step);

        for (int i = 1; i <= step; i++) {
            FactorialCalculator calculator = new FactorialCalculator((i - 1) * stepNum + 1, stepNum * i, countDownLatch);
            Future<BigDecimal> future = executor.submit(calculator);
            futures.add(future);
        }

        System.out.println("main thread await at :" + Instant.now().toEpochMilli());
        countDownLatch.await();
        System.out.println("main thread awake at :" + Instant.now().toEpochMilli());

        BigDecimal ret = new BigDecimal(1);
        for (int i = 0; i < futures.size(); i++) {
            Future<BigDecimal> future = futures.get(i);
            ret = ret.multiply(future.get());
        }
        executor.shutdown();
        return ret;
    }

    /**
     * 用于计算阶乘的任务
     */
    public static class FactorialCalculator implements Callable<BigDecimal> {

        private int start;
        private int end;
        private CountDownLatch countDownLatch;

        public FactorialCalculator(int start, int end, CountDownLatch countDownLatch) {
            this.start = start;
            this.end = end;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public BigDecimal call() {
            BigDecimal bigDecimal = new BigDecimal(1);
            log.info("multiplication ,start :{},end:{}, timestamp:{}", start, end, Instant.now().toEpochMilli());
            for (int i = start; i <= end; i++) {
                bigDecimal = bigDecimal.multiply(new BigDecimal(i));
            }
            countDownLatch.countDown();
            return bigDecimal;
        }
    }


}
