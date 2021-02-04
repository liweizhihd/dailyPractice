package com.weizhi.prac.leetcodepp.alg3;

import java.util.LinkedList;

/**
 * 思路: 参考https://leetcode-cn.com/problems/decode-string/solution/decode-string-fu-zhu-zhan-fa-di-gui-fa-by-jyd/
 *
 * @author liweizhi
 * @date 2021/2/4
 */
public class D4N394DecodeString {
    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        LinkedList<Integer> stackMulti = new LinkedList<>();
        LinkedList<String> stackRes = new LinkedList<>();
        for (Character c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                multi = multi * 10 + Integer.parseInt(c + "");
            } else if (c == '[') {
                stackMulti.addLast(multi);
                stackRes.addLast(res.toString());
                multi = 0;
                res = new StringBuilder();
            } else if (c == ']') {
                StringBuilder tmp = new StringBuilder();
                int curMulti = stackMulti.removeLast();
                for (int i = 0; i < curMulti; i++) {
                    tmp.append(res);
                }
                String lastRes = stackRes.removeLast();
                res = new StringBuilder(lastRes.length() + tmp.length())
                        .append(lastRes).append(tmp);
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

    public String myCopy(String s) {
        StringBuilder sb = new StringBuilder();

        LinkedList<Integer> multiStack = new LinkedList<>();
        LinkedList<String> tmpStringStack = new LinkedList<>();
        int multi = 0;
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                multi = multi * 10 + Integer.parseInt(c + "");
            } else if (c == '[') {
                multiStack.addLast(multi);
                multi = 0;
                tmpStringStack.addLast(sb.toString());
                sb = new StringBuilder();
            } else if (c == ']') {
                Integer currentMulti = multiStack.removeLast();
                StringBuilder sb2 = new StringBuilder(currentMulti * sb.length());
                for (int i = 0; i < currentMulti; i++) {
                    sb2.append(sb.toString());
                }

                String lastStr = tmpStringStack.removeLast();
                sb = new StringBuilder(lastStr.length() + sb2.length())
                        .append(lastStr).append(sb2);
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        D4N394DecodeString obj = new D4N394DecodeString();
        String s = "3[a2[c]]";
        System.out.println(obj.decodeString(s));
        System.out.println(obj.myCopy(s));
    }
}
