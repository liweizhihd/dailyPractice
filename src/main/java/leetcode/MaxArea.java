package leetcode;

/**
 * @Auther: liweizhi
 * @Date: 2019/2/11 16:40
 * @Description:
 */
public class MaxArea {
    public static void main(String[] args) {
        System.out.println(maxArea2(new int[]{1, 2, 3, 4, 5}));
    }

    public static int maxArea2(int[] height) {
        int ret = 0;
        int head = 0;
        int tail = height.length - 1;
        while (head < tail) {
            ret = Math.max(ret, (tail - head) * Math.min(height[head], height[tail]));
            if (height[head] > height[tail]) {
                tail--;
            } else {
                head++;
            }
        }
        return ret;
    }

    public static int maxArea(int[] height) {
        int ret = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                ret = Math.max(ret, (j - i) * Math.min(height[i], height[j]));
            }
        }
        return ret;
    }
}
