package leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Date;

/**
 * @Auther: liweizhi
 * @Date: 2018/12/25 21:00
 * @Description:
 */
@Slf4j
public class LongestPalindrome {

    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        String ret = s.substring(0, 1);

        for (int i = s.length(); i >= 0; i--) {
            for (int j = 0; j < i - 1; j++) {

                if (ret.length() >= (i + 1 - j)) {
                    continue;
                }

                String substring = s.substring(j, i);
                if (isPalindrome(substring)) {
                    ret = substring;
                }
            }
        }

        return ret;
    }

    private static boolean isPalindrome(String string) {
        int length = string.length();
        for (int i = 0; i <= length / 2; i++) {
            if (string.charAt(i) != string.charAt(length - 1 - i)) {
                return false;
            }
        }
        return true;

    }

    public static void main(String[] args) {
        String a = "asqddas";
        System.out.println(longestPalindrome(a));
    }

    @Test
    public void temp(){
        // new Date(1545720371)
        //1545720371
        //1545803310356
        System.out.println(new Date(1545720371L));
        System.out.println(new Date(1545720371000L));
    }
}
