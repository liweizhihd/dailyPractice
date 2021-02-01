package test.java8.lambda;

import org.junit.Test;

import java.util.Comparator;

/**
 * @auther: liweizhi
 * Date: 2019/3/27 09:59
 * Description:
 */
public class Test001 {
    static int outerStaticNum;
    int outerNum;

    @Test
    public void testScopes() {
        Converter<Integer, String> stringConverter1 = (from) -> {
            outerNum = 23;
            return String.valueOf(from + outerNum);
        };

        Converter<Integer, String> stringConverter2 = (from) -> {
            outerStaticNum = 72;
            return String.valueOf(from + outerStaticNum);
        };
    }

    @Test
    public void constructor() {
        Converter<String, Pet> petStringConverter = Dog::new;
        System.out.println(petStringConverter.convert("dahuang"));
    }


    public static void main(String[] args) {
        Comparator<Integer> c1 = Comparator.comparingInt(i -> i);
        Comparator<Integer> c2 = (o1, o2) -> o2 - o1;
        Comparator<Integer> c3 = (o1, o2) -> o1 - o2;

    }

}
