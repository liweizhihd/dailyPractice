package leetcode.toBeContinued;

/**
 * @Auther: liweizhi
 * @Date: 2019/2/1 15:42
 * @Description:未完成 先放一放了
 */
public class MatchString {
    public static void main(String[] args) {
        String s = "qqq";
        String p = "qw*q*e*q";
        System.out.println(isMatch(s, p));
    }

    public static boolean isMatch(String s, String p) {

        if (s == null) {
            if (p != null) {
                return false;
            }
            return true;
        }

        MyPattern myPattern = new MyPattern(p);
        int i = 0;
        int match = 0;
        while (i < s.length()) {
            match = myPattern.match(s.charAt(i));
            if (match == 0) {
                return false;
            } else if (match == 1 || match == 3) {
                i++;
            }
        }

        if (p.length() - 1 >= myPattern.getIndexNow() + myPattern.getLastStarMatch()) {
            return false;
        }

        return true;
    }


    public static class MyPattern {
        private String value;
        private int indexNow = 0;
        private int lastStarMatch = 0;

        /**
         * @param c
         * @return int : 0-不匹配；1-正常匹配；2-*不匹配;3-*匹配
         */
        public int match(char c) {
            // 超长
            if (indexNow > value.length() - 1) {
                return 0;
            }

            char charNow = value.charAt(indexNow);

            // 下一个字符是*
            if ((value.length() - 1 > indexNow) && (value.charAt(indexNow + 1) == '*')) {
                // *匹配
                if (value.charAt(indexNow) == c || charNow == '.') {
                    lastStarMatch++;
                    return 3;
                } else {
                    indexNow = indexNow + 2;
                    return 2;
                }
            }
            // 正常匹配
            if (charNow == c || charNow == '.') {
                indexNow++;
                lastStarMatch = 0;
                return 1;
            }
            return 0;//
        }

        public char nowChar() {
            return value.charAt(indexNow);
        }

        public char nextChar() {
            if (value.length() == indexNow + 1) {
                return '1';
            }
            return value.charAt(indexNow + 1);
        }

        public MyPattern(String value) {
            super();
            this.value = value;
        }

        public int getIndexNow() {
            return indexNow;
        }

        public int getLastStarMatch() {
            return lastStarMatch;
        }

    }
}
