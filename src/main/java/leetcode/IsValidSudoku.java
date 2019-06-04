package leetcode;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: liweizhi
 * @Date: 2019/5/20 09:54
 * @Description: https://leetcode-cn.com/problems/valid-sudoku/
 */
public class IsValidSudoku {

    @Test
    public void mainM() {
        String s = "[[\"5\",\"3\",\".\",\".\",\"7\",\".\",\".\",\".\",\".\"],[\"6\",\".\",\".\",\"1\",\"9\",\"5\",\".\",\".\",\".\"],[\".\",\"9\",\"8\",\".\",\".\",\".\",\".\",\"6\",\".\"],[\"8\",\".\",\".\",\".\",\"6\",\".\",\".\",\".\",\"3\"],[\"4\",\".\",\".\",\"8\",\".\",\"3\",\".\",\".\",\"1\"],[\"7\",\".\",\".\",\".\",\"2\",\".\",\".\",\".\",\"6\"],[\".\",\"6\",\".\",\".\",\".\",\".\",\"2\",\"8\",\".\"],[\".\",\".\",\".\",\"4\",\"1\",\"9\",\".\",\".\",\"5\"],[\".\",\".\",\".\",\".\",\"8\",\".\",\".\",\"7\",\"9\"]]";
        char[][] board = JSON.parseObject(s, char[][].class);
        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
        System.out.println("-------------------------------");
        System.out.println(isValidSudoku(board));
        System.out.println(isValidSudoku002(board));
    }

    public boolean isValidSudoku002(char[][] board) {
        HashSet<Character> rowSet = new HashSet<>(16);
        HashMap<Character, Integer>[] columns = new HashMap[9];
        HashMap<Character, Integer>[] boxes = new HashMap[9];
        for (int i = 0; i < 9; i++) {
            columns[i] = new HashMap<>(16);
            boxes[i] = new HashMap<>(16);
        }
        for (int i = 0; i < 9; i++) {
            rowSet.clear();
            for (int j = 0; j < 9; j++) {
                char item = board[i][j];
                if (item == '.') continue;
                // 行
                if (rowSet.contains(item)) return false;
                rowSet.add(item);
                // 列
                Integer columnCount = columns[j].getOrDefault(item, 0);
                if (columnCount > 0) return false;
                columns[j].put(item, 1);
                // 九宫格
                int boxIndex = (i / 3) * 3 + j / 3;
                Integer boxCount = boxes[boxIndex].getOrDefault(item, 0);
                if (boxCount > 0) return false;
                boxes[boxIndex].put(item, 1);
            }
        }
        return true;
    }

    public boolean isValidSudoku(char[][] board) {
        Set<Character> lineSet = new HashSet<>(16);
        Set<Character> columnSet = new HashSet<>(16);
        Set<Character> squareSet = new HashSet<>(16);
        for (int i = 0; i < 9; i++) {
            lineSet.clear();
            columnSet.clear();
            for (int j = 0; j < 9; j++) {
                // 行验证
                char lineItem = board[i][j];
                if ('.' != lineItem) {
                    if (lineSet.contains(lineItem)) return false;
                    lineSet.add(lineItem);
                }
                // 列验证
                char columnItem = board[j][i];
                if ('.' != columnItem) {
                    if (columnSet.contains(columnItem)) return false;
                    columnSet.add(columnItem);
                }
            }
        }
        // 九宫格验证
        for (int l = 0; l < 3; l++) {
            for (int i = 0; i < 9; i++) {
                if (i % 3 == 0) squareSet.clear();
                for (int j = 0; j < 3; j++) {
                    char squareItem = board[i][j + 3 * l];
                    if (squareItem != '.') {
                        if (squareSet.contains(squareItem)) return false;
                        squareSet.add(squareItem);
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(0 % 3);
    }
}
