package leetcode;

import org.junit.Test;

/**
 * @auther: liweizhi
 * Date: 2019/3/26 11:32
 * Description:
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 */
public class StrStr {

    @Test
    public void mainMethod() {
        String a = "hello";
        String b = "lla";
        System.out.println(strStr(a,b));
    }

    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

}
