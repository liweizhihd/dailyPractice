package com.weizhi.prac.leetcode.hot100;

import java.util.Deque;
import java.util.LinkedList;

public class N20 {

    public boolean isValid(String s) {
        Deque<Character> stack = new LinkedList<>();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (aChar == '(' || aChar == '[' || aChar == '{') {
                stack.addLast(aChar);
            } else if (stack.isEmpty()) {
                return false;
            } else if (aChar == ')') {
                if (stack.peekLast() == '(') {
                    stack.removeLast();
                } else {
                    return false;
                }
            } else if (aChar == ']') {
                if (stack.peekLast() == '[') {
                    stack.removeLast();
                } else {
                    return false;
                }
            } else if (aChar == '}') {
                if (stack.peekLast() == '{') {
                    stack.removeLast();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
