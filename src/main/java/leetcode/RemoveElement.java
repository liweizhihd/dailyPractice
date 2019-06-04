package leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @auther: liweizhi
 * Date: 2019/3/25 15:20
 * Description:
 */
public class RemoveElement {
    @Test
    public void mainMethod() {
        int[] nums = {1,1,2,1,3,4,5};
        System.out.println(removeElement(nums, 1));
        System.out.println(Arrays.toString(nums));
    }

    public int removeElement(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }
        int index = -1;
        int ret = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                ret--;
                if (index == -1) {
                    index = i;
                }
            } else {
                if (index != -1) {
                    nums[index] = nums[i];
                    index++;
                }
            }
        }
        return ret;
    }
}
