package leetcode;

import java.util.LinkedList;

/**
 * @auther: liweizhi
 * Date: 2019/3/15 14:35
 * Description:
 */
public class BracketisValid {
    public static void main(String[] args) {
        System.out.println(isValid("([)]"));
    }

    public static boolean isValid(String s) {
        char oneLeft = '(';
        char oneRight = ')';
        char twoLeft = '{';
        char twoRight = '}';
        char threeLeft = '[';
        char threeRight = ']';
        LinkedList<Character> leftChars = new LinkedList<>();

        for (char item : s.toCharArray()) {
            if (item == oneRight) {
                if (leftChars.size() == 0 || leftChars.getLast() != oneLeft) {
                    return false;
                } else {
                    leftChars.removeLast();
                }
            } else if (item == twoRight) {
                if (leftChars.size() == 0 || leftChars.getLast() != twoLeft) {
                    return false;
                } else {
                    leftChars.removeLast();
                }
            } else if (item == threeRight) {
                if (leftChars.size() == 0 || leftChars.getLast() != threeLeft) {
                    return false;
                } else {
                    leftChars.removeLast();
                }
            } else {
                leftChars.add(item);
            }
        }
        if (leftChars.size() > 0) {
            return false;
        }
        return true;
    }

}
