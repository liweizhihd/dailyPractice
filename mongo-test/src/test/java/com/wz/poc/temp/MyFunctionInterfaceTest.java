package com.wz.poc.temp;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author liweizhi
 * @date 2021/1/21
 */
public class MyFunctionInterfaceTest {
    public static void main(String[] args) {
        testMethod("   aaaa  ", String::trim);
        testMethod("   aaaa  ", s -> s.trim().toUpperCase());
    }

    public static void testMethod(String str, Function<String, String> func) {
        System.out.println(func.apply(str));
    }
}
