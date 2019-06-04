package leetcode;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther: liweizhi
 * @Date: 2019/2/13 10:04
 * @Description: 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 */
public class ThreeSum {
    public static void main(String[] args) {
        int[] nums = {1,-1,-1,0};
        System.out.println(StringUtils.join(nums, ','));
        System.out.println(threeSum2(nums));
        //System.out.println(threeSum3(nums));
    }

    public static List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> ret = new LinkedList<>();
        Arrays.sort(nums);
        int left, right;
        int nowData, leftData, rightData, goal;
        for (int i = 0; i < nums.length - 2; ) {
            if (nums[i] > 0) {
                break;
            }
            left = i + 1;
            right = nums.length - 1;
            goal = -nums[i];
            while (left < right) {
                if (nums[left] + nums[right] == goal) {
                    LinkedList<Integer> item = new LinkedList<>();
                    item.add(nums[i]);
                    item.add(nums[left]);
                    item.add(nums[right]);
                    ret.add(item);

                    // 去重
                    leftData = nums[left];
                    while (left < right && nums[left] == leftData) {
                        left++;
                    }
                    rightData = nums[right];
                    while (left < right && nums[right] == rightData) {
                        right--;
                    }
                } else if (nums[left] + nums[right] < goal) {
                    left++;
                } else {
                    right--;
                }
            }
            nowData = nums[i];
            while (i < nums.length && nums[i] == nowData) {
                i++;
            }
        }
        return ret;
    }

    public static List<List<Integer>> threeSum3(int[] nums) {
        Arrays.sort(nums);// 对于nums进行排序
        List<List<Integer>> result = new LinkedList<>();
        for (int i = 0; i < nums.length - 2; ) {
            if (nums[i] > 0) break;
            int j = i + 1, k = nums.length - 1;
            int need = -nums[i];
            while (j < k) {
                if (nums[j] + nums[k] < need) {
                    j++;
                } else if (nums[j] + nums[k] > need) {
                    k--;
                } else {
                    List<Integer> temp = new LinkedList<>();
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    temp.add(nums[k]);
                    result.add(temp);
                    int j_data = nums[j];
                    int k_data = nums[k];
                    while (j < k && j_data == nums[j]) j++;
                    while (j < k && k_data == nums[k]) k--;
                }
            }
            // 换i
            int i_data = nums[i];
            while (i < nums.length && nums[i] == i_data) i++;
        }
        return result;
    }


    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>(10);
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                int tmp = nums[i] + nums[j];
                for (int k = j + 1; k < nums.length; k++) {
                    if (tmp + nums[k] == 0) {
                        ret.add(Stream.of(nums[i], nums[j], nums[k]).sorted().collect(Collectors.toList()));
                    }
                }
            }
        }
        ret = ret.stream().distinct().collect(Collectors.toList());

        return ret;
    }
}
