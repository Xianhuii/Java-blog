package stream;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Demo02 {
    public static void demo01() {
        int[] ints = new int[]{1,2,3,4,5,6,7,8,9};
        IntStream intStream = Arrays.stream(ints);
        int intSum = intStream.sum();
        long[] longs = new long[]{1L,2L,3L,4L,5L,6L,7L,8L,9L};
        LongStream longStream = Arrays.stream(longs);
        long longSum = longStream.sum();
        double[] doubles = new double[]{1.0D,2.0D,3.0D,4.0D,5.0D,6.0D,7.0D,8.0D,9.0D};
        DoubleStream doubleStream = Arrays.stream(doubles);
        double doubleSum = doubleStream.sum();
        System.out.println(intSum); // 45
        System.out.println(longSum);    // 45
        System.out.println(doubleSum);  // 45.0
    }

    public static void demo02() {
        String[] strings = new String[]{"Hello", ", ", "Stream", "!"};
        Stream<String> stringStream = Arrays.stream(strings);
        long count = stringStream.count();
        System.out.println(count);  // 4
    }

    public static void demo03() {
        byte[] bytes = new byte[]{1,2,3,4,5,6,7,8,9};
        Stream<byte[]> byteStream = Stream.of(bytes);
        System.out.println(byteStream.count()); // 1
    }

    public static void demo04() {
        Byte[] bytes = new Byte[]{1,2,3,4,5,6,7,8,9};
        Stream<Byte> byteStream = Stream.of(bytes);
        System.out.println(byteStream.count()); // 9
    }

    public static void main(String[] args) {
//        demo01();
//        demo02();
//        demo03();
        demo04();
    }
}
