package others.problems;

import lombok.Data;

/**
 * @auther: liweizhi
 * Date: 2019/4/10 18:43
 * Description:
 */
@Data
public class Cat extends Pet{
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.setName("dahuang");
        Cat cat = new Cat();
        cat.setName("miao");
        System.out.println(dog.getName());
        System.out.println(cat.getName());
    }
}
