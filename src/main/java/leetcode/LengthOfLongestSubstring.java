package leetcode;

import java.util.*;

/**
 * @Auther: liweizhi
 * @Date: 2018/12/18 19:16
 * @Description:
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {

        System.out.println(new Date(1545580785514L));
    }

    public static int lengthOfLongestSubstring3(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        for (int i = 0, j = 0; j < n; j++) {

        }
        return ans;
    }

    public static int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    public static int lengthOfLongestSubstring(String s) {
        int ret = 0;

        Set<Character> currentSet = new HashSet<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (currentSet.contains(s.charAt(j))) {
                    ret = ret > currentSet.size() ? ret : currentSet.size();
                    currentSet.clear();
                    break;
                } else {
                    currentSet.add(s.charAt(j));
                }
            }
        }
        ret = ret > currentSet.size() ? ret : currentSet.size();
        return ret;
    }

}
