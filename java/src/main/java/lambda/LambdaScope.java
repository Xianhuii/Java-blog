package lambda;

import java.util.function.Consumer;
import java.util.function.Function;

public class LambdaScope {
    public static int count = 0;
    static class FI1 {
        static <T, R> void fun(T t, Function<T, R> function) {
            function.apply(t);
        }
    }
    static interface FI {
        void run();
    }
    static class FI2 {
        static void fun(FI fi) {
            fi.run();
        }
    }

    public static void demo01() {
        int a = 1;
        int b = 2;
        FI1.fun(a, t -> {
            int c = 3;
            System.out.println(b);
            System.out.println(t);
            return t;
        });
//        System.out.println(c); 报错
    }

    public static void demo02() {
        int a = 1;
        FI2.fun(() -> {
            int b = 2;
            System.out.println(a);
        });
//        System.out.println(b);
    }

    public static void demo03() {
        String str = "Hello, lambda!";
        FI2.fun(new FI() {
            @Override
            public void run() {
                System.out.println(str);
            }
        });
    }

    public void demo04() {
        FI2.fun(() -> {
            System.out.println(this);
            System.out.println(super.getClass());
        });
    }

    public static void demo05() {
        FI2.fun(() -> {
            LambdaScope.count++;
        });
    }

    public static void main(String[] args) {
//        demo01();
//        demo02();
//        demo03();
//        new LambdaScope().demo04();
        demo05();
        demo05();
        demo05();
        demo05();
        demo05();
        System.out.println(count);
    }
}
