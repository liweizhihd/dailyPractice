package leetcode;

import org.junit.Test;

/**
 * @Auther: liweizhi
 * @Date: 2019/5/30 10:54
 * @Description: https://leetcode-cn.com/problems/count-and-say/
 */
public class CountAndSay {

    public String countAndSay(int n) {
        String item = "";
        for (int i = 1; i <= n; i++) {
            item = say(item);
            //System.out.printf("i:%d,item:%s \n", i, item);
        }
        return item;
    }

    private String say(String item) {
        if (item.length() == 0) return "1";
        StringBuilder sb = new StringBuilder(item.length() + 5);
        char[] chars = item.toCharArray();
        int count = 1;
        for (int i = 0; i < chars.length; i++) {
            if (i < chars.length - 1) {
                if (chars[i] == chars[i + 1]) {
                    count++;
                } else {
                    sb.append(count);
                    if (i == 0 || count == 1) {
                        sb.append(chars[i]);
                    } else {
                        sb.append(chars[i - 1]);
                    }
                    count = 1;
                }
            } else {
                if (i > 0 && chars[i] == chars[i - 1]) {
                    sb.append(count);
                } else {
                    sb.append(1);
                }
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }

    @Test
    public void mainM() {
        System.out.println(countAndSay(30));
    }

    @Test
    public void check() {
        System.out.println(say("1234"));
    }

}
