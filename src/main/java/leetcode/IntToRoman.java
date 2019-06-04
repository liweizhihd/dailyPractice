package leetcode;

/**
 * @Auther: liweizhi
 * @Date: 2019/2/12 18:43
 * @Description:
 */
public class IntToRoman {
    public static void main(String[] args) {

        System.out.println(intToRoman2(3));
    }

    public static String intToRoman2(int num) {
        StringBuilder sb = new StringBuilder(16);

        int[] values = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < values.length; i++) {
            int value = values[i];
            while (num >= value) {
                num -= value;
                sb.append(symbols[i]);
            }
        }

        return sb.toString();
    }

    public static String intToRoman(int num) {
        StringBuilder sb = new StringBuilder(16);
        int qian = num / 1000;
        num = num % 1000;
        for (int i = 0; i < qian; i++) {
            sb.append("M");
        }

        int bai = num / 100;
        num = num % 100;
        sb.append(handle(bai, "C", "D", "M"));

        int shi = num / 10;
        num = num % 10;
        sb.append(handle(shi, "X", "L", "C"));

        sb.append(handle(num, "I", "V", "X"));

        return sb.toString();
    }

    public static String handle(int quotient, String one, String five, String ten) {
        StringBuilder sb = new StringBuilder(16);
        if (quotient > 0 && quotient < 4) {
            for (int i = 0; i < quotient; i++) {
                sb.append(one);
            }
        } else if (quotient == 4) {
            sb.append(one).append(five);
        } else if (quotient == 5) {
            sb.append(five);
        } else if (quotient > 5 && quotient < 9) {
            sb.append(five);
            for (int i = 0; i < quotient - 5; i++) {
                sb.append(one);
            }
        } else if (quotient == 9) {
            sb.append(one).append(ten);
        }
        return sb.toString();
    }
}
