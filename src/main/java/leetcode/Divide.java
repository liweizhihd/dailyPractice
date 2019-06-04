package leetcode;

import org.junit.Test;

/**
 * @auther: liweizhi
 * Date: 2019/3/26 14:32
 * Description:
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 */
public class Divide {
    public static void main(String[] args) {
        System.out.println(Math.abs(Integer.MIN_VALUE));
        System.out.println(Math.abs((long) Integer.MIN_VALUE));
    }

    @Test
    public void mainMethod() {
        System.out.println(divide(100, 3));
    }

    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        boolean minus = (dividend ^ divisor) < 0;
        long dd = Math.abs((long) dividend);
        long dr = Math.abs((long) divisor);
        if (dd < dr) {
            return 0;
        }
        int ret = 0;
        for (int i = 31; i >= 0; i--) {
            long l = dd >> i;
            if (l > dr) {
                ret += 1 << i;
                dd -= dr << i;
            }else if (l == dr){
                ret =  1 << i + 1;
                break;
            }
        }
        return minus ? -ret : ret;
    }

    /**
     * 解题思路：这题是除法，所以先普及下除法术语
     * 商，公式是：(被除数-余数)÷除数=商，记作：被除数÷除数=商...余数，是一种数学术语。
     * 在一个除法算式里，被除数、余数、除数和商的关系为：(被除数-余数)÷除数=商，记作：被除数÷除数=商...余数，
     * 进而推导得出：商×除数+余数=被除数。
     * <p>
     * 要求商，我们首先想到的是减法，能被减多少次，那么商就为多少，但是明显减法的效率太低
     * <p>
     * 那么我们可以用位移法，因为计算机在做位移时效率特别高，向左移1相当于乘以2，向右位移1相当于除以2
     * <p>
     * 我们可以把一个dividend（被除数）先除以2^n，n最初为31，不断减小n去试探,当某个n满足dividend/2^n>=divisor时，
     * <p>
     * 表示我们找到了一个足够大的数，这个数*divisor是不大于dividend的，所以我们就可以减去2^n个divisor，以此类推
     * <p>
     * 我们可以以100/3为例
     * <p>
     * 2^n是1，2，4，8...2^31这种数，当n为31时，这个数特别大，100/2^n是一个很小的数，肯定是小于3的，所以循环下来，
     * <p>
     * 当n=5时，100/32=3, 刚好是大于等于3的，这时我们将100-32*3=4，也就是减去了32个3，接下来我们再处理4，同样手法可以再减去一个3
     * <p>
     * 所以一共是减去了33个3，所以商就是33
     * <p>
     * 这其中得处理一些特殊的数，比如divisor是不能为0的，Integer.MIN_VALUE和Integer.MAX_VALUE
     */
    public int divide2(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        boolean negative = (dividend ^ divisor) < 0;//用异或来计算是否符号相异
        long t = Math.abs((long) dividend);
        long d = Math.abs((long) divisor);
        if (t < d) {
            return 0;
        }
        int result = 0;
        for (int i = 31; i >= 0; i--) {
            if ((t >> i) >= d) {//找出足够大的数2^n*divisor
                result += 1 << i;//将结果加上2^n
                t -= d << i;//将被除数减去2^n*divisor
            }
        }
        return negative ? -result : result;//符号相异取反
    }


}
