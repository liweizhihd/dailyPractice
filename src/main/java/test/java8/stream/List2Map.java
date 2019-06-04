package test.java8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @auther: liweizhi
 * Date: 2019/4/17 21:58
 * Description:
 */
public class List2Map {
    public static void main(String[] args) {
        List<DateValueBO> list = new ArrayList<DateValueBO>() {{
            add(new DateValueBO("04/17", 1));
            add(new DateValueBO("04/16", 2));
        }};
        Map<String, DateValueBO> collect = list.stream().parallel().collect(Collectors.toMap(k -> k.getDate(), v -> v));
        System.out.println(collect);
    }
}
