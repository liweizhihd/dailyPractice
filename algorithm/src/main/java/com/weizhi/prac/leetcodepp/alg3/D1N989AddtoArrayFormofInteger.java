package com.weizhi.prac.leetcodepp.alg3;

import java.util.LinkedList;
import java.util.List;

/**
 * For a non-negative integer X, the array-form of X is an array of its digits in left to right order.  For example, if X = 1231, then the array form is [1,2,3,1].
 * <p>
 * Given the array-form A of a non-negative integer X, return the array-form of the integer X+K.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-to-array-form-of-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 思路:
 * K是个数值,可以利用K来帮我们做"进1";
 * 输出结果我们用LinkedList表示,可以在头部插入数据
 * 从右到左遍历A,令 K = A[i] + K,然后去K最右面一位数加到链表头,然后把K的最右面一位抹掉;
 * 再考虑K的位数比A的位数多的情况,K按位数遍历,插入链表头
 *
 * @author liweizhi
 * @date 2021/2/1
 */
public class D1N989AddtoArrayFormofInteger {

    public static void main(String[] args) {
        D1N989AddtoArrayFormofInteger obj = new D1N989AddtoArrayFormofInteger();
        int[] A = {1, 2, 3, 4, 5};
        int K = 123456;
        System.out.println(obj.addToArrayForm(A, K));
    }

    public List<Integer> addToArrayForm(int[] A, int K) {
        LinkedList<Integer> ret = new LinkedList<>();

        for (int i = A.length - 1; i >= 0; i--) {
            if (K > 0) {
                K = A[i] + K;
                ret.addFirst(K % 10);
                K = K / 10;
            } else {
                ret.addFirst(A[i]);
            }
        }
        // 考虑K的位数比A多的情况
        while (K > 0) {
            ret.addFirst(K % 10);
            K = K / 10;
        }
        return ret;
    }
}
