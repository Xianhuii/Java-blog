package stream;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

public class Demo01 {
    public static void main(String[] args) {
        demo01();
        demo02();
        demo03();
        demo04();
        demo05();
        demo06();
    }

    public static void demo01() {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int count = 0;
        for (int i = 0; i < nums.length; ++i) {
            // 1、遍历得到每个元素
            int num = nums[i];
            // 2、对每个元素进行处理
            if (num % 2 == 1) {
                ++count;
            }
        }
        System.out.println(count);
    }

    public static void demo02() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Iterator<Integer> iterator = nums.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            // 1、遍历得到每个元素
            int num = iterator.next();
            // 2、对每个元素进行处理
            if (num % 2 == 1) {
                ++count;
            }
        }
        System.out.println(count);
    }

    public static void demo03() {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int count = 0;
        // 1、遍历得到每个元素
        for (int num : nums) {
            // 2、对每个元素进行处理
            if (num % 2 == 1) {
                ++count;
            }
        }
        System.out.println(count);
    }

    public static void demo04() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        int count = 0;
        // 1、遍历得到每个元素
        for (int num : nums) {
            // 2、对每个元素进行处理
            if (num % 2 == 1) {
                ++count;
            }
        }
        System.out.println(count);
    }

    public static void demo05() {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        // 1、获取原始数组的流
        IntStream intStream = Arrays.stream(nums);
        // 2、获取过滤后的流
        IntStream filterStream = intStream.filter(num -> num % 2 == 1);
        // 3、统计过滤后的流中元素的个数
        long count = filterStream.count();
        System.out.println(count);
    }

    public static void demo06() {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        long count = Arrays.stream(nums).filter(num -> num % 2 == 1).count();
        System.out.println(count);
    }
}
