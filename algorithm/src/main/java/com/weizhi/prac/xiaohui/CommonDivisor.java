package com.weizhi.prac.xiaohui;

import org.junit.Test;

/**
 * @Auther: liweizhi
 * @Date: 2019/5/9 11:30
 * @Description:
 */
public class CommonDivisor {

    @Test
    public void mainM() {
    }

    public int getCommonDivisor(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;
        if (a < b) {
            a = a ^ b;
            b = a ^ b;// a ^ b ^ b = a
            a = a ^ b;// a ^ b ^ a = b
        } else if (a == b) {
            return a;
        }
        if (!oddNumberFlag(a) && !oddNumberFlag(b)) {
            return getCommonDivisor(a >> 1, b >> 1) << 1;
        } else if (!oddNumberFlag(a)) {
            return getCommonDivisor(a >> 1, b);
        } else if (!oddNumberFlag(b)) {
            return getCommonDivisor(a, b >> 1);
        } else {
            return getCommonDivisor(a - b, b);
        }
    }

    public boolean oddNumberFlag(int number) {
        if ((number & 1) == 1) {
            return true;
        }
        return false;
    }

}
