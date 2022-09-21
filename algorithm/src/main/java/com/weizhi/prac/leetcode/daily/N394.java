package com.weizhi.prac.leetcode.daily;

import java.util.Deque;
import java.util.LinkedList;

public class N394 {

    public static void main(String[] args) {
        String s = "2[c1[a]2[b]]";
        N394 obj = new N394();
        System.out.println(obj.decodeString(s));
    }

    public String decodeString(String s) {
        Deque<StringBuilder> strStack = new LinkedList<>();
        Deque<Integer> multiStack = new LinkedList<>();
        char[] chars = s.toCharArray();
        int k = 0;
        StringBuilder ret = new StringBuilder();
        for (char aChar : chars) {
            if (aChar >= '0' && aChar <= '9') {
                k = k * 10 + aChar - '0';
            } else if (aChar == '[') {
                multiStack.addLast(k);
                strStack.addLast(ret);
                ret = new StringBuilder();
                k = 0;
            } else if (aChar == ']') {
                Integer multi = multiStack.removeLast();
                StringBuilder inner = new StringBuilder();
                for (int j = 0; j < multi; j++) {
                    inner.append(ret);
                }
                ret = new StringBuilder(strStack.removeLast()).append(inner);
            } else {
                ret.append(aChar);
            }
        }

        return ret.toString();
    }

}
