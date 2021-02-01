package com.weizhi.prac.forkjoin;

import java.math.BigDecimal;

/**
 * @author liweizhi
 * @date 2020/12/31
 */
public class ForLoopCalculator implements Calculator {
    @Override
    public BigDecimal factorial(long number) {
        BigDecimal ret = new BigDecimal(1);
        for (long i = 1; i <= number; i++) {
            ret = ret.multiply(BigDecimal.valueOf(i));
        }
        return ret;
    }
}
