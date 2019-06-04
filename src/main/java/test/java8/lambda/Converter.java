package test.java8.lambda;

/**
 * @auther: liweizhi
 * Date: 2019/3/27 09:56
 * Description:
 */
@FunctionalInterface
public interface Converter<F, T> {
    T convert(F from);

    default void ping(){
        System.out.println("pang");
    }
}
