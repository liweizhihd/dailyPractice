package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther: liweizhi
 * Date: 2019/3/16 12:26
 * Description:给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 */
public class GenerateParenthesis {
    public static void main(String[] args) {
        long term = 30L;
        long count = 1L;
        long in = 2L;

        System.out.println((double) count / term);
    }

    public static <E> void testy(List<E> list) {
        list.forEach(item -> {
            System.out.println(item);
        });
    }

    @Test
    public void test() {
        List<String> strings = generateParenthesis(3);
        System.out.println(strings);
    }

    public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList();
        generateAllMine(combinations, "", n, n, n * 2);
        return combinations;
    }

    public void generateAllMine(List<String> ret, String item, int leftRemain, int rightRemain, int total) {
        if (item.length() == total) {
            ret.add(item);
            return;
        }
        if (leftRemain > 0) {
            generateAllMine(ret, item + "(", leftRemain - 1, rightRemain, total);
        }
        if (rightRemain > leftRemain) {
            generateAllMine(ret, item + ")", leftRemain, rightRemain - 1, total);
        }
    }

    public void generateAll2(List<String> ret, String now, int left, int right, int pairs) {
        if (now.length() == pairs * 2) {
            ret.add(now);
            return;
        }
        if (left < pairs) {
            generateAll2(ret, now + "(", left + 1, right, pairs);
        }
        if (right < left) {
            generateAll2(ret, now + ")", left, right + 1, pairs);
        }
    }

    public void generateAll(char[] current, int pos, List<String> result) {
        //System.out.println(Arrays.toString(current));
        if (pos == current.length) {
            if (valid(current))
                result.add(new String(current));
        } else {
            current[pos] = '(';
            generateAll(current, pos + 1, result);
            current[pos] = ')';
            generateAll(current, pos + 1, result);
        }
    }

    public boolean valid(char[] current) {
        int balance = 0;
        for (char c : current) {
            if (c == '(') balance++;
            else balance--;
            if (balance < 0) return false;
        }
        return (balance == 0);
    }
}
