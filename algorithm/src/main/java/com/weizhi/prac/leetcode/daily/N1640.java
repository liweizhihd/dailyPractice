package com.weizhi.prac.leetcode.daily;

import java.util.HashMap;
import java.util.Map;

public class N1640 {
    public static void main(String[] args) {
        N1640 obj = new N1640();
        int[] arr = new int[]{91, 4, 64, 78};
        int[][] pieces = new int[][]{{78}, {4, 64}, {91}};
        System.out.println(obj.canFormArray(arr, pieces));
    }

    // arr = [91,4,64,78], pieces = [[78],[4,64],[91]]
    public boolean canFormArray(int[] arr, int[][] pieces) {
        Map<Integer, Integer> map = new HashMap<>(pieces.length);
        for (int i = 0; i < pieces.length; i++) {
            map.put(pieces[i][0], i);
        }

        for (int i = 0; i < arr.length; ) {
            Integer pieceIdx = map.get(arr[i]);
            if (pieceIdx == null) {
                return false;
            }
            int[] piece = pieces[pieceIdx];
            for (int j = 0; j < piece.length; j++, i++) {
                if (arr[i] != piece[j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
