package leetcode;/**
 * @Auther: liweizhi
 * @Date: 2019/1/29 09:48
 * @Description:
 */

/**
 * @Description 字符串转换整数
 * @Author liweizhi
 * Date 2019/1/29 上午9:48
 */
public class Atoi {
    public static void main(String[] args) {
        String s = " -";
        System.out.println(myAtoi(s));
    }

    // 空格：32，0：48，9：57
    public static int myAtoi(String str) {
        StringBuilder sb = new StringBuilder(32);
        boolean findFirstChar = false;
        boolean isFirstChar = true;
        boolean positive = true;
        for (char c : str.toCharArray()
        ) {
            if (!findFirstChar && !Character.isWhitespace(c)) {
                findFirstChar = true;
            }
            if (findFirstChar) {
                if (isFirstChar) { // 是第一个非空格字符
                    if ('-' == c) {
                        sb.append("-");
                        positive = false;
                    } else if ('+' == c) {
                    } else if (Character.isDigit(c)) {
                        sb.append(c);
                    } else {
                        return 0;
                    }
                    isFirstChar = false;
                } else {
                    if (Character.isDigit(c)) {
                        sb.append(c);
                    } else {
                        break;
                    }
                }
            }
        }
        if (sb.length() > 0) {
            if (sb.length() == 1 && (sb.toString().equals("-") || sb.toString().equals("+"))) {
                return 0;
            }
            try {
                return Integer.valueOf(sb.toString());
            } catch (NumberFormatException e) {
                if (positive) {
                    return Integer.MAX_VALUE;
                } else {
                    return Integer.MIN_VALUE;
                }
            }
        }
        return 0;
    }
}
