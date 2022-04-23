package stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.BinaryOperator;
import java.util.stream.*;

/**
 * 中间操作的案例
 *
 * @author jianxianhui
 */
public class IntermediateOpDemo {
    public static IntStream intStream = Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9});
    public static Stream<Integer> refStream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
    public static Stream<int[]> refStream2 = Stream.of(new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}});


    /**
     * filter(XxxPredicate)：过滤元素
     * lambda表达式接收元素作为形参，返回true表示保留该元素，返回false表示舍去该元素：
     * (ele) -> {
     * return true或false;
     * }
     */
    @Test
    public void demo01() {
        intStream.filter(i -> i > 5).forEach(System.out::print);
        System.out.println();
        refStream.filter(obj -> obj.compareTo(5) > 0).forEach(System.out::print);
    }

    /**
     * map()：对每个元素进行处理，返回处理后的元素
     * 基本数据类型流（IntStream/LongStream/DoubleStream）只能返回相同类型的元素，如int → int
     * 引用数据类型流（Stream）可以转换成任意引用类型，如Integer → String
     */
    @Test
    public void demo02() {
        IntStream intStream = IntermediateOpDemo.intStream.map(i -> i * 100);
        intStream.forEach(System.out::print);
        System.out.println();
        Stream<String> stringStream = refStream.map(obj -> "str:" + obj + ",");
        stringStream.forEach(System.out::print);
    }

    /**
     * mapToXxx()：映射-可以将流转成其他3中数据类型流
     * boxed()可以将基本数据类型流中的元素转化成对应的包装类型，因此流也变成了对应的Stream<Xxx>
     */
    @Test
    public void demo03() {
        IntStream intStream = refStream.mapToInt(obj -> obj);
//        LongStream longStream = refStream.mapToLong(obj -> obj);
//        DoubleStream doubleStream = refStream.mapToDouble(obj -> obj);
        System.out.println(intStream.sum());
        Stream<Integer> integerStream = intStream.mapToObj(i -> i);
//        Stream<Integer> integerStream = intStream.boxed();
        LongStream longStream = intStream.mapToLong(i -> i);
        DoubleStream doubleStream = intStream.mapToDouble(i -> i);
    }

    /**
     * flatMap()：展开映射，可以将二维层次的元素展开，映射到一个Stream中
     * 例如：
     * [
     * [1,2,3,4,5],
     * [6,7,8,9,10]
     * ]
     * 展开映射成：
     * IntStream：1,2,3,4,5,6,7,8,9,10
     * 例如：查找数据库时返回：List<User>，每个User都包含List<Book>，需要收集所有user的Book到一个List<Book>
     * List<Book> books = userList.stream().flatMap(user->user.getBooks().stream).collect(Collector.toList)
     * flatMapToInt()/flatMapToLong()/flatMapToDouble()可以将将二维层次的元素展开，映射到对应的XxxStream中
     */
    @Test
    public void demo04() {
        Stream<Integer> flatStream = refStream2.flatMap(arr -> Arrays.stream(arr).boxed());
        flatStream.forEach(System.out::print);
    }

    /**
     * distinct()：去重
     */
    @Test
    public void demo05() {
        intStream.distinct().forEach(System.out::print);
    }

    /**
     * sorted()：基本数据类型-从小到大排序；引用数据类型-转成java.lang.Comparable，再调用compareTo()方法排序
     */
    @Test
    public void demo06() {
        intStream.sorted().forEach(System.out::print);
        System.out.println();
        refStream.sorted().forEach(System.out::print);
        System.out.println();
        refStream2.sorted().forEach(System.out::print); // 报错：因为元素（int[]）不能转成java.lang.Comparable
    }

    /**
     * Stream.sorted(Comparator<? super T> comparator)：使用comparator进行排序
     */
    @Test
    public void demo07() {
        refStream.sorted(Integer::compareTo).forEach(System.out::print);
        System.out.println();
        refStream2.sorted(Comparator.comparingInt(arr -> -Arrays.stream(arr).sum())).forEach(arr -> System.out.print(Arrays.toString(arr)));
    }

    /**
     * peek()：获取每个元素进行操作
     * 对于可变引用类型数据，可以修改元素的属性值
     * 对于基本数据类型和不可变引用类型（String、Integer等），无法修改元素值
     */
    @Test
    public void demo08() {
//        int sum = intStream.peek(System.out::print).sum();
//        System.out.println(" sum: " + sum);
        long count = refStream.peek(System.out::print).count();
        System.out.println(" count: " + count);
        // 基本数据类型元素无法修改元素值，遍历出结果与初始一致
        intStream.peek(i -> {
                    i++;
                }
        ).forEach(System.out::print);
    }

    /**
     * limit()：限制元素的个数
     */
    @Test
    public void demo09() {
        intStream.limit(3).forEach(System.out::print);
    }

    /**
     * skip()：跳过n个元素
     */
    @Test
    public void demo10() {
        refStream.skip(3).forEach(System.out::print);
    }

    /**
     * forEach()：对每个元素进行遍历操作，终止操作
     *      顺序流：按顺序遍历
     *      并行流：顺序可能会变
     */
    @Test
    public void demo11() {
        refStream.parallel().sorted().forEach(System.out::print);
    }

    /**
     * forEachOrdered()：按顺序对每个元素进行遍历，终止操作
     */
    @Test
    public void demo12() {
        intStream.parallel().sorted().forEachOrdered(System.out::print);
    }
}
