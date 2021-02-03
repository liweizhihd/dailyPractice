package com.weizhi.prac.leetcodepp.alg3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a string S and a character C, return an array of integers representing the shortest distance from the character C in the string.
 * <p>
 * Example 1:
 * Input: S = "loveleetcode", C = 'e'
 * Output: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
 * <p>
 * Note:
 * S string length is in [1, 10000].
 * C is a single character, and guaranteed to be in string S.
 * All letters in S and C are lowercase.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shortest-distance-to-a-character
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 思路:
 * 每个字符S[i],左右两边都有可能出现C,找到距离最近的那个,然后相减得出距离
 * 两次遍历,先从左到右,然后从右到左
 * 1.首先从左到右遍历,假设最近一次的C出现的索引index为左边无穷远,设为index=-10000,
 * 遍历时,随时记录下最近一次C出现的索引=index,然后结果就是S[i] - index
 * 2.从右到左遍历,假设最近一次的C出现的索引index为右边无穷远,设index=10000,
 * 遍历时,随时记录下最近一次C出现的索引=index,然后结果就是 min(index-S[i], 上一次从左到右遍历时得到的结果)
 *
 * @author liweizhi
 * @date 2021/2/2
 */
public class D2N821ShortestDistancetoaCharacter {

    public static void main(String[] args) {
        D2N821ShortestDistancetoaCharacter obj = new D2N821ShortestDistancetoaCharacter();
        String S = "loveleetcode";
        char C = 'e';
        int[] ints = obj.shortestToChar(S, C);
        System.out.println(Arrays.toString(ints));
    }

    public int[] shortestToChar(String S, char C) {
        char[] chars = S.toCharArray();

        int[] ret = new int[chars.length];
        int lastIndex = -10000;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == C) {
                lastIndex = i;
            }
            ret[i] = i - lastIndex;
        }
        lastIndex = 10000;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == C) {
                lastIndex = i;
            }
            ret[i] = Math.min(ret[i], lastIndex - i);
        }
        return ret;
    }
}
