package stream;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Demo03 {
    public static void demo01() {
        // 考试分数数组
        int[] nums = new int[]{59,59,59,59,59,59,60,60,98,99,100};
        // 第一步：创建流
        IntStream numsStream = Arrays.stream(nums);
        // 第二部：逻辑处理：过滤考试分数大于等于60分的元素
        IntStream filteredStream = numsStream.filter(num -> num >= 60);
        // 第三步：收集结果：计算元素个数
        long count = filteredStream.count();
        System.out.println(count);  // 5
    }

    public static void demo02() {
        // 考试分数数组
        int[] nums = new int[]{59,59,59,59,59,59,60,60,98,99,100};
        // 第一步：创建流
        IntStream numsStream = Arrays.stream(nums);
        // 第二部：逻辑处理：过滤考试分数大于等于60分的元素
        IntStream filteredStream = numsStream.filter(num -> num >= 60);
        // 第三步：收集结果：计算元素个数
        long count = filteredStream.count();
        System.out.println(count);  // 5
        IntStream intStream = filteredStream.filter(num -> num >= 80);
//        System.out.println(filteredStream.sum());
    }

    public static void demo03() {
        // 考试分数数组
        int[] nums = new int[]{59,59,59,59,59,59,60,60,98,99,100};
        // 第一步：创建流
        IntStream numsStream = Arrays.stream(nums);
        // 第二部：逻辑处理：过滤考试分数大于等于60分的元素
        IntStream filteredStream = numsStream.filter(num -> num >= 60);
        // 第三步：收集结果：计算元素的加和
        long sum = filteredStream.reduce(0, Integer::sum);
        System.out.println(sum);  // 417
    }

    public static void main(String[] args) {
//        demo01();
//        demo02();
        demo03();
    }
}
