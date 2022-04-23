package stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.function.BinaryOperator;

public class TerminalOpDemo {
    public static int[] ints = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    public static String[] refs = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    /**
     * toArray()：收集成数组（基本数据类型或Object）
     * 例如：
     *      IntStream intStream = ……;
     *      int[] intArr = intStream.toArray();
     *      List<User> users = ……;
     *      Object[] userArr = users.stream().toArray();// 类似于toArray(Object[]::new)
     */
    @Test
    public void demo1() {
        int[] intArr = Arrays.stream(ints).toArray();
        System.out.println(Arrays.toString(intArr));
    }

    /**
     * toArray(IntFunction<A[]> generator)：收集成对应类型数组
     * 例如：
     *      List<User> users = ……;
     *      User[] userArr = users.stream().toArray(User[]::new);
     */
    @Test
    public void demo2() {
        String[] integers = Arrays.stream(refs).toArray(String[]::new);
        System.out.println(Arrays.toString(integers));
    }

    /**
     * reduce()：累加操作
     * 执行逻辑类似于：
     * <pre>
     *      boolean foundAny = false;
     *      T result = null;  // 累加结果
     *      for (T element : this stream) {
     *          if (!foundAny) {
     *              foundAny = true;
     *              result = element;   // 获取第一个元素，作为累加结果
     *          }
     *          else
     *              result = accumulator.apply(result, element);    // 将累加结果与当前元素进行apply()操作
     *      }
     *      return foundAny ? OptionalInt.of(result) : OptionalInt.empty();
     * </pre>
     */
    @Test
    public void demo3() {
        OptionalInt optionalRes = Arrays.stream(ints).reduce((res, ele) -> res + ele);
        int result = optionalRes.isPresent() ? optionalRes.getAsInt() : 0;
        System.out.println(result);
    }

    /**
     * reduce(T identity, BinaryOperator<T> accumulator)：累加操作
     * 执行逻辑类似于：
     * <pre>
     *      T result = identity;  // 累加结果初始化为identity
     *      for (T element : this stream) {
     *          result = accumulator.apply(result, element);    // 将累加结果与当前元素进行apply()操作
     *      }
     *      return result;
     * </pre>
     */
    @Test
    public void demo4() {
        int result = Arrays.stream(ints).reduce(100, (res, ele) -> res + ele);
        System.out.println(result);
    }

    /**
     * reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner)：累加操作
     * 执行逻辑类似于：
     * <pre>
     *      U result = identity;
     *      for (T element : this stream)
     *          result = accumulator.apply(result, element)
     *      return result;
     * </pre>
     */
    @Test
    public void demo5() {
//        int res = intStream.reduce(0, (identity, ele) -> identity - ele);
//        OptionalInt optionalRes = intStream.reduce((identity, ele) -> identity - ele);
//        int res = optionalRes.getAsInt();
        String res = Arrays.stream(refs).reduce("0",
                (a, b) -> a + b,
                BinaryOperator.maxBy((a, b) -> Integer.parseInt(a + b)));
        System.out.println(res);
    }
}
