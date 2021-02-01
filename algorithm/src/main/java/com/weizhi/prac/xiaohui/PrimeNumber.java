package com.weizhi.prac.xiaohui;

import org.junit.Test;

/**
 * @Auther: liweizhi
 * @Date: 2019/5/10 15:04
 * @Description:
 */
public class PrimeNumber {

    @Test
    public void primeNum001() {
        long start = System.currentTimeMillis();
        int a, b;
        for (a = 2; a <= 100; a++) {
            for (b = 2; b <= (a / b); b++) {
                if (a % b == 0) {
                    break;
                }
            }
            if (b > (a / b)) {
                System.out.printf("%d\t是素数,b=%d\n", a, b);
            }
        }
        long end = System.currentTimeMillis();
        System.out.printf("耗时：%d 毫秒", end - start);
    }

    @Test
    public void primeNum002() {
        long start = System.currentTimeMillis();
        for (int i = 2; i <= 100; i++) {
            boolean flag = true;
            for (int j = 2; j < Math.sqrt(i); j++) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                System.out.printf("%d\t是素数\n", i);
        }
        long end = System.currentTimeMillis();
        System.out.printf("耗时：%d 毫秒", end - start);
    }

    public static void main(String[] args) {
        System.out.println(74.96 - 20.48);
        System.out.println(74.96 * 20.48);
        System.out.println(0.6F + 0.7F);
    }
}
