package com.prac.demo1.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: liweizhi
 * @Date: 2019/1/29 14:36
 * @Description:
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExecuteTime {
    /**
     * 打印标识符
     * @return
     */
    String value() default "undefined";
}
