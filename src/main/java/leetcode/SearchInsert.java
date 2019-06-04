package leetcode;

import org.junit.Test;

/**
 * @Auther: liweizhi
 * @Date: 2019/5/17 18:03
 * @Description:
 * https://leetcode-cn.com/problems/search-insert-position/
 */
public class SearchInsert {

    @Test
    public void mainM(){
        int[] nums = {1,3,5,6};
        int ints = searchInsert(nums, 7);
        System.out.println(ints);
    }

    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        return binarySearch(nums, 0, nums.length - 1, target);
    }

    public int binarySearch(int[] nums, int start, int end, int target) {
        if (nums[end] < target) return end + 1;
        if (nums[end] == target) return end;
        if (nums[start] >= target) return start;
        int mid = (start + end) >> 1;
        int midNum = nums[mid];
        if (midNum == target) {
            return mid;
        } else if (target > midNum) {
            return binarySearch(nums, mid + 1, end - 1, target);
        } else if (target < midNum) {
            return binarySearch(nums, start + 1, mid - 1, target);
        }
        return -1;
    }
}
