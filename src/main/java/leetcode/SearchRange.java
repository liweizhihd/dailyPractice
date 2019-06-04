package leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Auther: liweizhi
 * @Date: 2019/5/17 17:08
 * @Description: https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class SearchRange {

    @Test
    public void mainM() {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int[] ints = searchRange(nums, 6);
        System.out.println(Arrays.toString(ints));
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        int index = binarySearch(nums, 0, nums.length - 1, target);
        if (index == -1) {
            return new int[]{-1, -1};
        }
        int start = index, end = index;
        while (start > 0 && nums[start] == nums[start - 1]) {
            start--;
        }
        while (end < nums.length - 1 && nums[end] == nums[end + 1]) {
            end++;
        }
        return new int[]{start, end};
    }

    public int binarySearch(int[] nums, int start, int end, int target) {
        int mid = (start + end) >> 1;
        int midNum = nums[mid];
        if (nums[start] == target) return start;
        if (nums[end] == target) return end;
        if (midNum == target) {
            return mid;
        } else if (target > midNum && target < nums[end]) {
            return binarySearch(nums, mid + 1, end - 1, target);
        } else if (target < midNum && target > nums[start]) {
            return binarySearch(nums, start + 1, mid - 1, target);
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(2 > 1);
    }
}
