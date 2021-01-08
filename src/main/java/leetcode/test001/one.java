package leetcode.test001;

import org.junit.Test;

/**
 * @Auther: liweizhi
 * @Date: 2019/8/12 22:40
 * @Description:
 */
public class one {

    @Test
    public void mainM() {
        System.out.println(maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
    }

    // 判断是否是平方数
    public boolean isPerfectSquare(int num) {
        for (int i = 1; i <= num; i++) {
            int i1 = num / i;
            if (i * i == num) {
                return true;
            } else if (i1 < i) {
                return false;
            }
        }
        return false;
    }

    // 最大利益
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int ret = 0;
        int left = prices[0];
        int temp = 0;
        for (int i = 1; i < prices.length - 1; i++) {
            if (left < prices[i]) {
                temp = prices[i] - left;
            }
            if (left > prices[i] && temp > 0) {
                ret += temp;
                temp = 0;
                left = prices[i];
            }
        }
        return ret;
    }

}
