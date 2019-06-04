package com.prac.demo1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @auther: liweizhi
 * Date: 2019/4/15 09:50
 * Description:
 */
public class TempTest {
    public static final Pattern PATTERN_IP = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");

    public static void main(String[] args) {
        String a = "asd(10.0.132.33)";
        Matcher matcher = PATTERN_IP.matcher(a);
        if (matcher.find()){
            System.out.println(matcher.group());
        }
    }
}
