package leetcode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @auther: liweizhi
 * Date: 2019/3/14 14:21
 * Description:
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {3, 4, 2};
        int target = 6;
        int[] ints = twoSum(nums, target);
        System.out.println(Arrays.toString(ints));
    }

    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> container = new HashMap<>(nums.length);
        for (int a = 0; a < nums.length; a++) {
            int need = target - nums[a];
            if (container.containsKey(need)) {
                return new int[]{container.get(need), a};
            }
            container.put(nums[a], a);
        }

        return null;
    }
}
