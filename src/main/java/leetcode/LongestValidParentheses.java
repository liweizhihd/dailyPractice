package leetcode;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Auther: liweizhi
 * @Date: 2019/5/15 20:48
 * @Description:
 */
public class LongestValidParentheses {

    @Test
    public void mainM() {
        String s = ")()(()";
        System.out.println(longestValidParentheses001(s));
    }

    public int myCopy(String s) {
        if (s == null || s.length() == 0) return 0;
        int ret = 0;
        int[] dp = new int[s.length()];
        char[] chars = s.toCharArray();
        int i = 0;
        for (char c : chars) {
            if (c == ')' && i > 0) {
                if (chars[i - 1] == '(') {
                    dp[i] = (i > 2 ? dp[i - 2] : 0) + 2;
                } else {
                    int left = i - dp[i - 1] - 1;
                    if (chars[i - 1] == ')' && left >= 0 && chars[left] == '(') {
                        dp[i] = dp[i - 1] + 2 + (left > 1 ? dp[left - 1] : 0);
                    }
                }
            }
            ret = Math.max(ret, dp[i]);
            i++;
        }
        return ret;
    }


    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] dp = new int[s.length()];
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i > 0 && s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i - 2 >= 0 ? dp[i - 2] + 2 : 2);
                } else {
                    // left是上一个有效括号块的左边，也就是对应i上的")"的"("
                    int left = i - dp[i - 1] - 1;
                    if (s.charAt(i - 1) == ')' && left >= 0 && s.charAt(left) == '(') {
                        dp[i] = dp[i - 1] + 2 + (left - 1 >= 0 ? dp[left - 1] : 0);
                    }
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // )()())
    public int longestValidParentheses001(String s) {
        if (s == null || s.length() == 0) return 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addFirst(-1);
        //System.out.println(stack);
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.addFirst(i);
            } else {
                stack.removeFirst();
                if (stack.isEmpty()) {
                    stack.addFirst(i);
                } else {
                    res = Math.max(res, i - stack.getFirst());
                }
            }
        }
        return res;
    }

}
