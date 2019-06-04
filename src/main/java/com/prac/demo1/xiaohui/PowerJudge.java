package com.prac.demo1.xiaohui;

import org.junit.Test;

/**
 * @Auther: liweizhi
 * @Date: 2019/5/10 09:14
 * @Description:
 */
public class PowerJudge {
    @Test
    public void mainM() {
        //System.out.println(powerJudge4(4));
        long judge = 0B1010101010101010101010101010101;
        long a = 0B1111111111111111111111111111111;
        System.out.println(judge);
        System.out.println(a);
        System.out.println(Integer.MAX_VALUE);
    }

    public boolean powerJudge4(long num) {
        long judge = 0B1010101010101010101010101010101;
        if ((num & (num - 1)) != 0) {
            return false;
        }
        if ((num & judge) == num) {
            return true;
        }
        return false;
    }
}
