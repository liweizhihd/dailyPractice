package leetcode;

import org.junit.Test;

import java.util.*;

/**
 * @auther: liweizhi
 * Date: 2019/3/28 20:53
 * Description:
 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 */
public class FindSubstring {

    public static void main(String[] args) {
        String a = "11111";
        System.out.println(a.indexOf("1", 1));
    }

    @Test
    public void mainMethod() {
        String s = "afoobarbar";
        String[] words = {"foo", "bar"};
        long start = System.currentTimeMillis();
        System.out.println(findSubstring(s, words));
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ret = new LinkedList<>();
        if (s == null || "".equals(s) || words == null || words.length == 0) {
            return ret;
        }

        int lengthEach = words[0].length();
        int lengthTotal = lengthEach * words.length;

        Map<String, Integer> originMap = generateCount(words);
        for (int i = 0; i < lengthEach; i++) {
            int left = i, right = i;
            Map<String, Integer> windowMap = new HashMap<>(16);

            while (right <= s.length() - lengthEach && left <= s.length() - lengthEach * words.length) {
                String sub = s.substring(right, right + lengthEach);
                incr(windowMap, sub);
                if (!originMap.containsKey(sub)) {
                    windowMap.clear();
                    right += lengthEach;
                    left = right;
                    continue;
                }

                while (windowMap.get(sub) > originMap.get(sub)) {
                    decr(windowMap, s.substring(left, left + lengthEach));
                    left += lengthEach;
                }
                right += lengthEach;
                if (right - left == lengthTotal) {
                    ret.add(left);
                }
            }
        }

        return ret;
    }

    // ------------------------------------------------------

    /**
     * 解题思路：这题咋一看好难，有种暴力法是把words所有组合可能列出来，然后一一对比，想着都耗时，肯定有更简便的方法
     * <p>
     * 我们分析题意，words中的字符长度是一致的，这个比较关键，假设words的长度是1,是不是感觉会简单许多
     * <p>
     * 这就变成字串中是否包含数组中所有字符，那我们就可以用移动窗口来查询窗口中是否包含所有字符
     * <p>
     * 想到查询，那用Hash表是最方便快捷的，我们可以定义两个Hash表，一个用来保存窗口中出现的字符串，一个用来保存数组中的字符串
     * <p>
     * Hash表key为字符串，value为出现的次数
     * <p>
     * 窗口有两个指针left和right，分别表示窗口的开始和结束，设每个单词的长度为l，每次取right+l作为一个单词，将这个单词存入窗口Hash表中
     * <p>
     * 假设这个单词在数组Hash表中不存在，说明这个区间不合格，所以需要将left置为right,right置为right+l继续找
     * <p>
     * 假设这个单词在数组Hash表中存在并且出现次数小于数组Hash表出现次数，那么将right加上l继续取，否则将left加上l
     * <p>
     * 当right-left的区间长度正好为数组中所有字符加起来的长度时，表示匹配上正确的字符了
     */
    public List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }
        Map<String, Integer> wordsCount = generateCount(words);//将所有字符加入数组Hash表
        int length = words[0].length();
        for (int i = 0; i < length; ++i) {//错位循环，保证每种情况都遍历到
            Map<String, Integer> window = new HashMap<>();
            int left = i;
            int right = i;
            while (right <= s.length() - length && left <= s.length() - length * words.length) {
                String sub = s.substring(right, right + length);
                incr(window, sub);//取一个字符加入窗口Hash表
                if (!wordsCount.containsKey(sub)) {//如果这个字符在数组Hash表中不存在，就清理窗口并重置left和right
                    window.clear();
                    right += length;
                    left = right;
                    continue;
                }
                while (window.get(sub) > wordsCount.get(sub)) {//当窗口Hash中字符次数多于数组Hash字符次数时，left+l,交从窗口中移除最左边的字符
                    decr(window, s.substring(left, left + length));
                    left += length;
                }
                right += length;
                if (right - left == length * words.length) {//当窗口长度正好等于数组字符总长度时，表示匹配成功，加入结果中
                    result.add(left);
                }
            }
        }
        return result;
    }

    private Map<String, Integer> generateCount(String[] words) {
        Map<String, Integer> wordsCount = new HashMap<>();
        for (String word : words) {
            incr(wordsCount, word);
        }
        return wordsCount;
    }

    private void incr(Map<String, Integer> map, String key) {
        if (map.containsKey(key)) {
            map.put(key, map.get(key) + 1);
        } else {
            map.put(key, 1);
        }
    }

    private void decr(Map<String, Integer> map, String key) {
        if (map.containsKey(key)) {
            Integer value = map.get(key);
            if (value <= 1) {
                map.remove(key);
            } else {
                map.put(key, value - 1);
            }
        }
    }

    // ------------------------------------------------------

    public List<Integer> findSubstring001(String s, String[] words) {
        int length = words[0].length();
        List<Integer> ret = new LinkedList<>();
        Set<String> allCombinations = getAllCombinations(new HashSet<>(), "", words);
        allCombinations.stream().parallel().forEach(item -> {
            int index = s.indexOf(item);
            while (index > -1) {
                ret.add(index);
                index = s.indexOf(item, index + length);
            }
        });
        return ret;
    }

    public Set<String> getAllCombinations(Set<String> ret, String item, String[] words) {
        if (words.length == 1) {
            ret.add(item + words[0]);
        } else {
            for (int i = 0; i < words.length; i++) {
                String[] next = new String[words.length - 1];
                System.arraycopy(words, 0, next, 0, i);
                System.arraycopy(words, i + 1, next, i, words.length - 1 - i);
                getAllCombinations(ret, item + words[i], next);
            }
        }
        return ret;
    }

}
