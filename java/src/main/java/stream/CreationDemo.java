package stream;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 创建流的案例
 * @author jianxianhui
 */
public class CreationDemo {
    /**
     * Arrays.stream()
     */
    @Test
    public void demo01() {
        int[] ints = new int[]{1,2,3,4,5,6,7,8,9};
        IntStream intStream = Arrays.stream(ints);
        intStream.forEach(System.out::print);
    }

    /**
     * Collection.stream()
     */
    @Test
    public void demo02() {
        List<Integer> integerList = Arrays.asList(1,2,3,4,5,6,7,8,9);
        Stream<Integer> integerStream = integerList.stream();
        integerStream.forEach(System.out::print);
    }

    /**
     * Stream.of()
     */
    @Test
    public void demo03() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        integerStream.forEach(System.out::print);
    }

    /**
     * Stream.empty()
     */
    @Test
    public void demo04() {
        Stream<Object> empty = Stream.empty();
        empty.forEach(System.out::print);
    }

    /**
     * Stream.builder()：使用建造者模式创建流
     */
    @Test
    public void demo05() {
        Stream<Object> objectStream = Stream.builder()
                .add(1)
                .add(2)
                .add(3)
                .add(4)
                .add(5)
                .add(6)
                .add(7)
                .add(8)
                .add(9)
                .build();
        objectStream.forEach(System.out::print);
    }

    /**
     * Stream.iterate()：生成无限流
     */
    @Test
    public void demo06() {
        Stream<Integer> integerStream = Stream.iterate(Integer.valueOf("0"), t -> t + 1);
        integerStream.forEach(System.out::println);
    }

    /**
     * Stream.generate()：生成无限流
     */
    @Test
    public void demo07() {
        Stream<String> stringStream = Stream.generate(() -> UUID.randomUUID().toString());
        stringStream.forEach(System.out::println);
    }

    /**
     * Stream.concat()：按顺序连接两个流
     */
    @Test
    public void demo08() {
        Stream<Integer> integerStream1 = Stream.of(1, 2, 3, 4);
        Stream<Integer> integerStream2 = Stream.of(5, 6, 7, 8, 9);
        Stream<Integer> integerStream = Stream.concat(integerStream1, integerStream2);
        integerStream.forEach(System.out::print);
    }
}
