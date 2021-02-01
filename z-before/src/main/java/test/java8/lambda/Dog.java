package test.java8.lambda;

import lombok.NonNull;

/**
 * @auther: liweizhi
 * Date: 2019/3/27 11:50
 * Description:
 */
public class Dog extends Pet {

    private String name;

    public Dog(@NonNull String name) {
        super(name);
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog{" + name + '}';
    }
}
