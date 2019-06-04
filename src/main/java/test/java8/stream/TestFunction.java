package test.java8.stream;

/**
 * @auther: liweizhi
 * Date: 2019/3/27 16:39
 * Description:
 */
@FunctionalInterface
public interface TestFunction {
    boolean isNull(Object obj);
    boolean equals(Object obj);
}
