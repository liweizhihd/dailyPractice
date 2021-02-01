package test.java8.stream;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther: liweizhi
 * Date: 2019/3/27 17:07
 * Description:
 */
public class Test002 {
    @Test
    public void mapTest(){
        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }

        map.computeIfPresent(3, (num, val) -> val + num);
        System.out.println(map.get(3));

        map.computeIfAbsent(3, num -> "bam");
        System.out.println(map.get(3));

        System.out.println(map.getOrDefault("a", "axiba"));
    }

}
