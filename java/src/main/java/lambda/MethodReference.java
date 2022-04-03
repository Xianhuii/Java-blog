package lambda;

import java.awt.*;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReference {
    static class FI1 {
        static <T> void accept(T t, Consumer<T> consumer) {
            consumer.accept(t);
        }
    }

    public static void demo01() {
        FI1.accept("Xianhuii", t -> {
            System.out.println(t);
        });
        FI1.accept("Xianhuii", t -> {
            PrintStream out = System.out;
            synchronized (out) {
                out.print(t);
                out.print("\n\b");
            }
        });

        FI1.accept("Xianhuii", System.out::println);
    }

    static class FI2 {
        static <T, R> R apply(T t, Function<T, R> function) {
            return function.apply(t);
        }
    }

    static class FI3 {
        static <R> R apply(Supplier<R> supplier) {
            return supplier.get();
        }
    }

    public static void demo02() {
        Date date1 = FI2.apply(System.currentTimeMillis(), t -> {
            return new Date(t);
        });
        Date date1C = FI2.apply(System.currentTimeMillis(), Date::new);
        System.out.println(date1);
        System.out.println(date1C);
        Date date2 = FI3.apply(() -> {
            return new Date();
        });
        Date dateC = FI3.apply(Date::new);
        System.out.println(date2);
        System.out.println(dateC);
    }

    public static void demo03() {
        Date[] date1 = FI2.apply(10, t -> {
            return new Date[t];
        });
        Date[] date1C = FI2.apply(10, (Function<Integer, Date[]>) Date[]::new);
        System.out.println(Arrays.toString(date1));
        System.out.println(Arrays.toString(date1C));
    }

    public static void main(String[] args) {
//        demo01();
//        demo02();
        demo03();
    }
}
