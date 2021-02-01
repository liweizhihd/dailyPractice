package others;

import lombok.Data;

import java.util.Objects;

/**
 * @Auther: liweizhi
 * @Date: 2019/1/17 15:59
 * @Description:
 */
public class RandomTest {
    public static void main(String[] args) {
        String a = new String("a");
        String a1 = new String("a");
        System.out.println(a==a1);
        System.out.println(a.equals(a1));
    }

    public static void change(int a){
        a++;
    }

    public static void clean(Animal animal){
        animal = new Animal();
        animal.setAge(0);
        animal.setName("zhangsan");
    }
    public static void cleanSelf(Animal animal){
        animal.setAge(0);
        animal.setName("zhangsan");
    }

    @Data
    public static class Animal{
        private int age;
        private String name;
        public Animal getCopy(){
            Animal copy = new Animal();
            copy.age = this.age;
            copy.name = this.name;
            return copy;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Animal animal = (Animal) o;
            return age == animal.age &&
                    Objects.equals(name, animal.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(age, name);
        }
    }

}
