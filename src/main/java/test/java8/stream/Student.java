package test.java8.stream;

import lombok.Data;
import lombok.NonNull;

/**
 * @Auther: liweizhi
 * @Date: 2019/2/13 11:13
 * @Description:
 */
@Data
public class Student implements Comparable<Student>, Cloneable {
    @NonNull
    private int no;
    @NonNull
    private String name;
    @NonNull
    private String sex;
    @NonNull
    private float height;
    @NonNull
    private Pet pet;

    @Override
    public int compareTo(Student other) {
        if (this == other) {
            return 0;
        }
        if (other == null) {
            return 1;
        }
        if (this.getHeight() != other.getHeight()) {
            return Float.compare(this.getHeight(), other.getHeight());
        }

        return 0;
    }

    @Override
    protected Student clone() throws CloneNotSupportedException {
        Student clone = (Student) super.clone();
        clone.setPet(this.pet.clone());
        return clone;
    }

    @Data
    public static class Pet implements Cloneable{
        @NonNull
        private String name;
        @NonNull
        private String color;

        @Override
        protected Pet clone() throws CloneNotSupportedException {
            return (Pet) super.clone();
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Student s = new Student(1,"xiaoming","M",166, new Pet("dahuang","yellow"));
        Student clone =  (Student)s.clone();
        System.out.println(clone);
        clone.setHeight(170);
        clone.getPet().setColor("green");
        s.getPet().setName("laohuang");
        System.out.println("s:" + s);
        System.out.println("clone:" + clone);
    }
}
