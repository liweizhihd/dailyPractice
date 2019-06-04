package com.prac.demo1;

import org.junit.Test;

/**
 * @auther: liweizhi
 * Date: 2019/4/11 20:27
 * Description:
 */
public class PrimeNumber {
    public static void main(String[] args) {
        int product = 707829217;
        int sqrt = (int) Math.sqrt(product);
        for (int i = sqrt; i > 5; i--) {
            if (isPrimeNumber(i) && product % i == 0) {
                System.out.println(i);
                System.out.println(product / i);
                break;
            }
        }
    }

    public static boolean isPrimeNumber(int num) {
        if (num <= 3) {
            return true;
        }
        // 不在6的倍数的两侧的一定不是质数
        if (num % 6 != 1 && num % 6 != 5) {
            return false;
        }
        int sqrt = (int) Math.sqrt(num);
        for (int i = 5; i <= sqrt; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void mainM() {
        int count = 0;
        for (int i = 1; i <= 33; i++) {
            if (isPrimeNumber(i)) {
                count++;
                System.out.println(count + "   " + i);
            }
        }
    }
}
