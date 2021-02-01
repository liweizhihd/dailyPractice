package com.weizhi.prac.forkjoin;

import java.math.BigDecimal;

/**
 * @author liweizhi
 * @date 2020/12/31
 */
public interface Calculator {
    /**
     * 求传进来数的阶乘
     *
     * @param number
     * @return 总和
     */
    BigDecimal factorial(long number);
}
