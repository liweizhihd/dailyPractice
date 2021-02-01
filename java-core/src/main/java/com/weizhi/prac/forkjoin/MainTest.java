package com.weizhi.prac.forkjoin;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

/**
 * @author liweizhi
 * @date 2020/12/31
 */
public class MainTest {
    public static void main(String[] args) {
        long numbers = 10_0000;

        Calculator forLoopCalculator = new ForLoopCalculator();
        Calculator executorServiceCalculator = new ExecutorServiceCalculator();
        Calculator forkJoinCalculator = new ForkJoinCalculator();

        Instant start, end;

        // 热热身
        forLoopCalculator.factorial(10000);

        start = Instant.now();
        BigDecimal result_1 = forLoopCalculator.factorial(numbers);
        end = Instant.now();
        System.out.println("forLoopCalculator耗时：" + Duration.between(start, end).toMillis() + "ms");

        start = Instant.now();
        BigDecimal result_2 = executorServiceCalculator.factorial(numbers);
        end = Instant.now();
        System.out.println("executorServiceCalculator：" + Duration.between(start, end).toMillis() + "ms");

        start = Instant.now();
        BigDecimal result_3 = forkJoinCalculator.factorial(numbers);
        end = Instant.now();
        System.out.println("forkJoinCalculator：" + Duration.between(start, end).toMillis() + "ms");

        System.out.println("三者是否相等" + (result_1.equals(result_2) && result_1.equals(result_3)));
    }
}
