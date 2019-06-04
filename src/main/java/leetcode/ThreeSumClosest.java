package leetcode;

import java.util.Arrays;

/**
 * @Auther: liweizhi
 * @Date: 2019/2/15 09:23
 * @Description:
 */
public class ThreeSumClosest {

    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 8, 16, 32, 64, 128};
        System.out.println(threeSumClosest(nums, 82));
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int closestSum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int sumThree = nums[i] + nums[j] + nums[k];
                if (Math.abs(target - closestSum) > Math.abs(target - sumThree)) {
                    closestSum = sumThree;
                }
                if (sumThree == target) {
                    return sumThree;
                } else if (sumThree < target) {
                    int jValue = nums[j];
                    while (jValue == nums[j] && j < k) {
                        j++;
                    }
                } else {
                    int kValue = nums[k];
                    while (kValue == nums[k] && j < k) {
                        k--;
                    }
                }
            }
        }
        return closestSum;
    }
}
