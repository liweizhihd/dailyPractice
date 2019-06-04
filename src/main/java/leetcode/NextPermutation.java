package leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: liweizhi
 * @Date: 2019/5/14 20:51
 * @Description: https://leetcode-cn.com/problems/next-permutation/
 * 想象一种的场景：
 * 4,5,9,8,7,6,3,2,1
 */
public class NextPermutation {
    @Test
    public void mainM() {

        int[] nums = {1, 3, 2};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            for (int j = nums.length - 1; j > i; j--) {
                if (nums[j] > nums[i]) {
                    swap(nums, i, j);
                    break;
                }
            }
        }
        reverse(nums, i + 1);
    }

    public void reverse(int[] nums, int start) {
        int end = nums.length - 1;
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public void swap(int[] nums, int indexA, int indexB) {
        nums[indexA] = nums[indexA] ^ nums[indexB];
        nums[indexB] = nums[indexA] ^ nums[indexB];
        nums[indexA] = nums[indexA] ^ nums[indexB];
    }

    public void nextPermutationF(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int rightIndex = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[rightIndex]) {
                nums[i] = nums[i] ^ nums[rightIndex];
                nums[rightIndex] = nums[i] ^ nums[rightIndex];
                nums[i] = nums[i] ^ nums[rightIndex];
                return;
            } else if (nums[i] == nums[rightIndex]) {
                rightIndex--;
            } else if (nums[i] > nums[rightIndex]) {
                nums[i] = nums[i] ^ nums[rightIndex];
                nums[rightIndex] = nums[i] ^ nums[rightIndex];
                nums[i] = nums[i] ^ nums[rightIndex];
                rightIndex--;
            }
        }
        Arrays.sort(nums);
    }

    static Boolean a;

    public static void main(String[] args) {
        System.out.println(a == null);

    }

}
