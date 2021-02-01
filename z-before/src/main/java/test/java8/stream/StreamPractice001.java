package test.java8.stream;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther: liweizhi
 * @Date: 2019/2/13 11:23
 * @Description:
 */
public class StreamPractice001 {
    static List<Student> list = new ArrayList<>();

    static {
        Student stuA = new Student(1, "A", "M", 184, new Student.Pet("dahuang", "yellow"));
        Student stuA02 = new Student(1, "A2", "M", 184, new Student.Pet("dahuang", "yellow"));
        Student stuB = new Student(2, "B", "G", 163, new Student.Pet("dahuang", "yellow"));
        Student stuC = new Student(3, "C", "M", 175, new Student.Pet("dahuang", "yellow"));
        Student stuD = new Student(4, "D", "G", 158, new Student.Pet("dahuang", "yellow"));
        Student stuE = new Student(5, "E", "M", 170, new Student.Pet("dahuang", "yellow"));
        list.add(stuA);
        list.add(stuA02);
        list.add(stuB);
        list.add(stuC);
        list.add(stuD);
        list.add(stuE);

    }

    public static void main(String[] args) {
        Map<Student, Long> collect = list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(JSON.toJSON(collect));
    }

    @Test
    public void reduce002() {
        Optional accResult = Stream.of(1, 2, 3, 4).reduce((acc, item) -> {
            System.out.println("acc : " + acc);
            acc += item;
            System.out.println("item: " + item);
            System.out.println("acc+ : " + acc);
            System.out.println("--------");
            return acc;
        });
        System.out.println("accResult: " + accResult.get());
    }

    public void reduce003() {
        ArrayList<Integer> accResult_ = Stream.of(1, 2, 3, 4).reduce(new ArrayList<Integer>(), new BiFunction<ArrayList<Integer>, Integer, ArrayList<Integer>>() {
            @Override
            public ArrayList<Integer> apply(ArrayList<Integer> acc, Integer item) {
                acc.add(item);
                System.out.println("item: " + item);
                System.out.println("acc+ : " + acc);
                System.out.println("BiFunction");
                return acc;
            }
        }, new BinaryOperator<ArrayList<Integer>>() {
            @Override
            public ArrayList<Integer> apply(ArrayList<Integer> acc, ArrayList<Integer> item) {
                System.out.println("BinaryOperator");
                //acc.addAll(item);
                System.out.println("item: " + item);
                System.out.println("acc+ : " + acc);
                System.out.println("--------");
                return acc;
            }
        });
        System.out.println("accResult_: " + accResult_);

    }

    @Test
    public void tomap001(){
        Map<Integer, Student> collect = list
                .stream()
                .collect(Collectors.toMap(key -> key.getNo(), value -> value));
        System.out.println(collect);
    }
    public void partitioningBy001() {
        Map<Boolean, List<Integer>> collect = Stream.of(1, 2, 3, 4, 5)
                .collect(Collectors.partitioningBy(v -> v % 2 == 0));
        System.out.println(collect);
        System.out.println(collect.get(true));
    }


    public void sort001() {
        Stream.of(5, 2, 1, 4, 3).sorted().forEach(System.out::println);
        Stream.of(5, 2, 1, 4, 3).sorted((o1, o2) -> o2 - o1).forEach(System.err::println);
    }

    public void foreachOrder001() {
        Stream.of(5, 2, 1, 4, 3)
                .forEachOrdered(integer -> {
                    System.out.println("integer:" + integer);
                });
        Stream.of(5, 2, 1, 4, 3)
                .forEach(integer -> {
                    System.err.println("integer:" + integer);
                });
    }

    public void map001() {
        Stream.of("1", "2", "3").mapToInt(value -> Integer.valueOf(value)).forEach(value -> System.out.println(value));

    }

    public void filter001() {
        list.stream().filter(student -> "G".equals(student.getSex()))
                .sorted((o1, o2) -> Integer.compare(o2.getNo(), o1.getNo()))
                .forEach(student -> System.out.println(student));
    }

    //@Test
    public void concat001() {
        Stream.concat(Stream.of(1, 3, 5), Stream.of(2, 4, 6)).forEach(integer -> System.out.println(integer));
    }

    @Test
    public void testTransfer() {
        LinkedTransferQueue<Integer> queue = new LinkedTransferQueue<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);  // 再改成1500
                    System.out.println(Thread.currentThread().getName()+"-"+queue.take());
                    System.out.println(Thread.currentThread().getName()+"-"+queue.take());
                    System.out.println(Thread.currentThread().getName()+"-"+queue.take());
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        },"consumer").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"-"+queue.tryTransfer(1));
                try {
                    System.out.println(Thread.currentThread().getName()+"-等待2被消耗："+queue.tryTransfer(2, 1, TimeUnit.SECONDS));
                    queue.transfer(3);
                    System.out.println(Thread.currentThread().getName()+"-"+"等到3被消费：true");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"prodcuer").start();
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
