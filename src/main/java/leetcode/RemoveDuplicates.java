package leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @auther: liweizhi
 * Date: 2019/3/25 14:58
 * Description:
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 */
public class RemoveDuplicates {
    @Test
    public void mainMethod() {
        int[] nums = {1, 1, 2, 3, 4};
        System.out.println(removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0){
            return 0;
        }
        int ret = 1;
        int index = -1;
        int lasItem = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (lasItem == nums[i]) {
                if (index == -1) {
                    index = i;
                }
            } else {
                lasItem = nums[i];
                ret++;
                if (index != -1) {
                    nums[index] = nums[i];
                    index++;
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        Integer i1 = 40;
        Integer i2 = 40;
        Integer i3 = 0;
        Integer i4 = new Integer(40);
        Integer i5 = new Integer(40);
        Integer i6 = new Integer(0);

        System.out.println("i1=i2   " + (i1 == i2));
        System.out.println("i1=i2+i3   " + (i1 == i2 + i3));
        System.out.println("i1=i4   " + (i1 == i4));
        System.out.println("i4=i5   " + (i4 == i5));
        System.out.println("i4=i5+i6   " + (i4 == i5 + i6));
        System.out.println("40=i5+i6   " + (40 == i5 + i6));
        System.out.println("i1=i5+i6   " + (i1 == i5 + i6));
    }
}
