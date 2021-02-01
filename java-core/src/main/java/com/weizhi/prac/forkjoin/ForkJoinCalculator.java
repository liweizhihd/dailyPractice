package com.weizhi.prac.forkjoin;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author liweizhi
 * @date 2020/12/31
 */
public class ForkJoinCalculator implements Calculator {
    @Override
    public BigDecimal factorial(long number) {
        return pool.invoke(new SumTask(1, number));
    }

    private static final ForkJoinPool pool = ForkJoinPool.commonPool();

    //执行任务RecursiveTask：有返回值  RecursiveAction：无返回值
    private static class SumTask extends RecursiveTask<BigDecimal> {
        private long from;
        private long to;

        public SumTask(long from, long to) {
            this.from = from;
            this.to = to;
        }

        //此方法为ForkJoin的核心方法：对任务进行拆分  拆分的好坏决定了效率的高低
        @Override
        protected BigDecimal compute() {
            // 当需要计算的数字个数小于1_0000时，直接采用for loop方式计算结果
            if (to - from < 1_0000) {
                BigDecimal ret = new BigDecimal(1);
                for (long i = from; i <= to; i++) {
                    ret = ret.multiply(BigDecimal.valueOf(i));
                }
                return ret;
            } else { // 否则，把任务一分为二，递归拆分(注意此处有递归)到底拆分成多少分 需要根据具体情况而定
                long middle = (from + to) / 2;
                SumTask taskLeft = new SumTask(from, middle);
                SumTask taskRight = new SumTask(middle + 1, to);
                taskLeft.fork();
                taskRight.fork();
                return taskLeft.join().multiply(taskRight.join());
            }
        }
    }

    public static void main(String[] args) {
        long numbers = 100_0000;
        ForkJoinCalculator forkJoinCalculator = new ForkJoinCalculator();
        Instant start = Instant.now();
        BigDecimal result = forkJoinCalculator.factorial(numbers);
        Instant end = Instant.now();
        System.out.println("result: " + result + ",\n 耗时:" + Duration.between(start, end).toMillis() + "毫秒");
    }
}
