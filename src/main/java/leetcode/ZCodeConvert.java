package leetcode;/**
 * @Auther: liweizhi
 * @Date: 2019/1/28 15:49
 * @Description:
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @Description Z 字形变换
 * @Author liweizhi
 * Date 2019/1/28 下午3:49
 */
public class ZCodeConvert {

    public static void main(String[] args) {
        String s = "abcdefghijklmnopqrstuvwxyz";
        System.out.println(convert(s, 4));
        System.out.println(convert2(s, 4));
    }

    public static String convert(String s, int numRows) {
        long start = System.currentTimeMillis();
        if (numRows < 1) {
            return "";
        }
        if (numRows == 1) {
            return s;
        }
        List<StringBuilder> retList = new ArrayList<>(numRows);
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            retList.add(new StringBuilder(numRows / 2));
        }
        int numRowsMinus = numRows - 1;
        int count = 0;
        boolean goDown = false;
        for (char c : s.toCharArray()
        ) {
            retList.get(count).append(c);
            if (count == numRowsMinus || count == 0) {
                goDown = !goDown;
            }
            count += goDown ? 1 : -1;
        }
        StringBuilder ret = retList.get(0);
        for (int i = 1; i < retList.size(); i++) {
            ret.append(retList.get(i));
        }
        long end = System.currentTimeMillis();
        System.out.println("convert()耗时:" + (end - start));
        return ret.toString();
    }

    public static String convert2(String s, int numRows) {
        long start = System.currentTimeMillis();
        if (numRows == 1) return s;

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                    ret.append(s.charAt(j + cycleLen - i));
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("convert2()耗时:" + (end - start));
        return ret.toString();
    }
}
