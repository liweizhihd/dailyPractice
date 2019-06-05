package leetcode;

import org.junit.Test;

import java.util.*;

/**
 * @Auther: liweizhi
 * @Date: 2019/6/5 11:38
 * @Description:
 */
public class CombinationSum {

    @Test
    public void maimM() {
        int[] candidates = new int[]{2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> lists = combinationSum001(candidates, target);
        System.out.println(lists);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        return null;
    }

    // 这是第一版 不好；执行用时 : 124 ms,内存消耗 : 56.2 MB
    public List<List<Integer>> combinationSum001(int[] candidates, int target) {
        // 排序
        Arrays.sort(candidates);
        Set<List<Integer>> ret = new HashSet<>();
        combinationSum(ret, new LinkedList<>(), 0, candidates, target);
        return new LinkedList<>(ret);
    }

    private void combinationSum(Set<List<Integer>> ret, List<Integer> innerList, int tempSum, int[] candidates, int target) {
        for (int candidate : candidates) {
            if (tempSum + candidate == target) {
                innerList.add(candidate);
                Collections.sort(innerList);
                ret.add(innerList);
            } else if (tempSum + candidate < target) {
                List<Integer> newInnerList = new LinkedList<>(innerList);
                newInnerList.add(candidate);
                combinationSum(ret, newInnerList, tempSum + candidate, candidates, target);
            } else {
                return;
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        List<Integer> list2 = new LinkedList<>();
        list.add(1);
        list.add(2);
        list2.add(2);
        list2.add(1);
        Set<List<Integer>> ret = new HashSet<>();
        //
        Collections.sort(list);
        Collections.sort(list2);
        ret.add(list);
        ret.add(list2);
        System.out.println(ret);
        LinkedList<List<Integer>> retList = new LinkedList<>(ret);
        System.out.println(retList);
    }
}
