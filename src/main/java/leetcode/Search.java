package leetcode;

import org.junit.Test;

/**
 * @Auther: liweizhi
 * @Date: 2019/5/16 14:38
 * @Description: https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 */
public class Search {

    @Test
    public void mainM() {
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 6));
    }

    public int search(int[] nums, int target) {
        int length = nums.length;
        if (nums == null || length == 0) {
            return -1;
        }
        return searchHelper(nums, target, 0, nums.length - 1);
    }

    public int searchHelper(int[] nums, int target, int start, int end) {
        if (start > end) {
            return -1;
        }
        int mid = (start + end) / 2;
        if (target == nums[start]) return start;
        if (target == nums[mid]) return mid;
        if (target == nums[end]) return end;
        if (nums[start] < nums[mid]) { // 左半截有序
            if (nums[start] < target && nums[mid] > target) {// 目标值在左半截中
                return binarySearch(nums, target, start + 1, mid - 1);
            }
            return searchHelper(nums, target, mid + 1, end - 1);
        }
        if (nums[mid] < nums[end]) { // 右半截有序
            if (nums[mid] < target && nums[end] > target) {// 目标值在右半截中
                return binarySearch(nums, target, mid + 1, end - 1);
            }
            return searchHelper(nums, target, start + 1, mid - 1);
        }
        return -1;
    }

    public int binarySearch(int[] nums, int target, int start, int end) {
        if (start <= end) {
            if (nums[start] == target) return start;
            if (nums[end] == target) return end;
            int mid = (start + end) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] > target) return binarySearch(nums, target, start + 1, mid - 1);
            if (nums[mid] < target) return binarySearch(nums, target, mid + 1, end - 1);
        }
        return -1;
    }

}
