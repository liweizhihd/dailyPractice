package test.java8.stream;

import lombok.Data;

/**
 * @auther: liweizhi
 * Date: 2019/4/16 21:04
 * Description:
 */
@Data
public class DateValueBO {
    private String date;
    private int value;

    public DateValueBO() {
    }

    public DateValueBO(String date, int value) {
        this.date = date;
        this.value = value;
    }
}
