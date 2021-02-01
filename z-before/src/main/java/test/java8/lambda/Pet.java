package test.java8.lambda;

import lombok.Data;
import lombok.NonNull;

/**
 * @auther: liweizhi
 * Date: 2019/3/27 11:41
 * Description:
 */
@Data
public class Pet {
    @NonNull
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
