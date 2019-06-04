package leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @Auther: liweizhi
 * @Date: 2019/2/22 19:02
 * @Description:
 */
public class LetterCombinations {
    public static void main(String[] args) {
        List<String> list = letterCombinations("2345");
        System.out.println(list);
    }

    public static List<String> letterCombinations(String numbers) {
        List<String> ret = new LinkedList<>();
        if (numbers == null || numbers.length() == 0) {
            return ret;
        }

        String[] arr = new String[numbers.length()];
        for (int i = 0; i < numbers.length(); i++) {
            switch (numbers.charAt(i)) {
                case '2':
                    arr[i] = "abc";
                    break;
                case '3':
                    arr[i] = "def";
                    break;
                case '4':
                    arr[i] = "ghi";
                    break;
                case '5':
                    arr[i] = "jkl";
                    break;
                case '6':
                    arr[i] = "mno";
                    break;
                case '7':
                    arr[i] = "pqrs";
                    break;
                case '8':
                    arr[i] = "tuv";
                    break;
                case '9':
                    arr[i] = "wxyz";
                    break;
                default:
                    System.err.println("参数错误");
                    return null;
            }
        }

        addItems(ret, arr, 0, "");
        return ret;
    }

    private static void addItems(List<String> ret, String[] arr, int index, String item) {
        String keyBroad = arr[index];
        if (index < arr.length - 1) {
            for (int i = 0; i < keyBroad.length(); i++) {
                addItems(ret, arr, index + 1, item + keyBroad.charAt(i));
            }
            index++;
        } else {
            for (int i = 0; i < keyBroad.length(); i++) {
                ret.add(item + keyBroad.charAt(i));
            }
        }
    }

}
