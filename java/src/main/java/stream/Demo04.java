package stream;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Demo04 {
    public static void demo01() {
        String[] strings = new String[]{"1","2","3","4"};
        Stream<String> stringStream = Arrays.stream(strings);
        int[] ints = new int[]{1,2,3,4};
        IntStream intStream = Arrays.stream(ints);
        long[] longs = new long[]{1L,2L,3L,4L};
        LongStream longStream = Arrays.stream(longs);
        double[] doubles = new double[]{1D,2D,3D,4D};
        DoubleStream doubleStream = Arrays.stream(doubles);
    }

    public static void demo02() {
        String[] strings = new String[]{"1","2","3","4"};
        // {"1","2","3","4"}
        Stream<String> stringStream = Arrays.stream(strings,0,4);
        int[] ints = new int[]{1,2,3,4};
        // {1,2,3}
        IntStream intStream = Arrays.stream(ints,0,3);
        long[] longs = new long[]{1L,2L,3L,4L};
        // {2L,3L}
        LongStream longStream = Arrays.stream(longs,1,3);
        double[] doubles = new double[]{1D,2D,3D,4D};
        // {2D}
        DoubleStream doubleStream = Arrays.stream(doubles,1,2);
    }


    public static void main(String[] args) {

    }
}
