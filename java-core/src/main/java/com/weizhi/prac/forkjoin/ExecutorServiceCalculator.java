package com.weizhi.prac.forkjoin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author liweizhi
 * @date 2020/12/31
 */
public class ExecutorServiceCalculator implements Calculator {
    @Override
    public BigDecimal factorial(long number) {
        List<Future<BigDecimal>> results = new ArrayList<>();

        // 把任务分解为 n 份，交给 n 个线程处理   4核心 就等分成4份呗
        // 然后把每一份都扔个一个SumTask线程 进行处理
        long pageSize = number / parallism;
        for (int i = 0; i < parallism; i++) {
            long from = i * pageSize + 1; //开始位置
            long to = i == parallism - 1 ? number : Math.min(from + pageSize - 1, number); //结束位置

            //扔给线程池计算
            results.add(pool.submit(new SumTask(from, to)));
        }

        // 把每个线程的结果相加，得到最终结果 get()方法 是阻塞的
        // 优化方案：可以采用CompletableFuture来优化  JDK1.8的新特性
        BigDecimal ret = BigDecimal.valueOf(1);
        for (Future<BigDecimal> f : results) {
            try {
                ret = f.get().multiply(ret);
            } catch (Exception ignore) {
            }
        }

        // 方便测试程序退出
        pool.shutdown();
        return ret;
    }

    private static final int parallism = Runtime.getRuntime().availableProcessors();
    private static final ExecutorService pool = Executors.newFixedThreadPool(parallism);

    //处理计算任务的线程
    private static class SumTask implements Callable<BigDecimal> {

        private long from;
        private long to;

        public SumTask(long from, long to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public BigDecimal call() {
            BigDecimal ret = new BigDecimal(1);
            for (long i = from; i <= to; i++) {
                ret = ret.multiply(BigDecimal.valueOf(i));
            }
            return ret;
        }
    }

}
