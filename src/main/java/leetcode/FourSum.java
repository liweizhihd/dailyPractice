package leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @auther: liweizhi
 * Date: 2019/3/14 11:06
 * Description:给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，
 * 使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 */
public class FourSum {
    public static void main(String[] args) {
        int[] nums = {1,-2,-5,-4,-3,3,3,5};
        int target = -11;
        List<List<Integer>> lists = fourSum(nums, target);
        System.out.println(lists);
    }

    public static List<List<Integer>> fourSum002(int[] nums, int target) {
        List<List<Integer>> ret = new LinkedList<>();

        return ret;
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ret = new LinkedList<>();
        Arrays.sort(nums);
        int iData, jData, left, right, myTarget, leftData, rightData;
        for (int i = 0; i <= nums.length - 4; ) {
            for (int j = nums.length - 1; j >= i + 3; ) {
                left = i + 1;
                right = j - 1;
                myTarget = target - nums[i] - nums[j];
                while (left < right) {
                    if (nums[left] + nums[right] == myTarget) {
                        List<Integer> now = new LinkedList<>();
                        now.add(nums[i]);
                        now.add(nums[left]);
                        now.add(nums[right]);
                        now.add(nums[j]);
                        ret.add(now);
                        // 去重
                        leftData = nums[left];
                        while (left < right && nums[left] == leftData) {
                            left++;
                        }
                        rightData = nums[right];
                        while (left < right && nums[right] == rightData) {
                            right--;
                        }
                    } else if (nums[left] + nums[right] > myTarget) {
                        right--;
                    } else {
                        left++;
                    }
                }
                jData = nums[j];
                while (j >= i + 3 && jData == nums[j]) {
                    j--;
                }
            }
            iData = nums[i];
            while (i <= nums.length - 4 && iData == nums[i]) {
                i++;
            }
        }
        return ret;
    }

    @Test
    public void continusTest() {
        for (int i = 0; i < 4; i++) {
            for (int j = i; j < 4; j++) {
                if (j == 2) {
                    continue;
                }
                System.out.println(i + " " + j);
            }
        }
    }

}
