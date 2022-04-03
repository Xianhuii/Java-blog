package lambda;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * lambda表达式基本用法
 * <ol>
 *      <li>lambda表达式本质上是对匿名内部类实例的一种简化写法：{@link LambdaExpression#demo01()}
 *      <li>lambda表达式基本语法：{@link LambdaExpression#demo02()}
 *      <li>lambda表达式的执行逻辑本质上是传递了一个匿名对象，是一种假的函数式编程：{@link LambdaExpression#demo03()}
 *      <li>编译器会根据lambda表达式动态实现接口，并实例化出对象：{@link LambdaExpression#demo04()}
 * </ol>
 *
 * @author Xianhuii
 * @date 2022/3/19 15:25
 */
public class LambdaExpression {
    /**
     * 在对{@link List}进行排序时，会用到{@link List#sort(Comparator)}方法，需要传递实现{@link Comparator}接口的对象作为参数。
     * <p>方式一：创建{@link Comparator}的实现类，并传递该实现类的对象。
     * <p>方式二：创建{@link Comparator}的匿名对象，并传递该匿名对象。
     * <p>方式三：使用lambda表达式。
     * <p>方式四：使用方法引用（具体查看：{@link MethodReference}）。
     * <p>
     * lambda表达式省略了复杂的代码，使得代码更加简单和简洁。
     */
    static void demo01() {
        List<Integer> list = Arrays.asList(1, 3, 5, 7, 9, 2, 4, 6, 8, 10);
        // 方式一：创建实现类对象{@link AscComparator}
        Comparator<Integer> ascComparator = new AscComparator();
        list.sort(ascComparator);
        // 方式二：匿名对象
        Comparator<Integer> anonymousComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
        list.sort(anonymousComparator);
        // 等价于
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        // 方式三：lambda表达式
        list.sort((o1, o2) -> o1.compareTo(o2));
        // 方式四：方法引用
        list.sort(Integer::compare);
        // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        System.out.println(list);
    }

    /**
     * Comparator<T>的实现类
     */
    static class AscComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    }

    /**
     * lambda表达式本质上是对接口的匿名实现类的一种简化写法：方法和lambda表达式的格式一一对应。
     * 对于编译器而言方法主要由两部分组成（没有返回值）：形参和方法体，而lambda表达式由三部分组成：
     * <p>  1、形参：(t1, t2, ……)，对应方法的形参(T1 t1, T2 t2, ……)
     * <p>  2、箭头：->，固定
     * <p>  3、方法体：{}，对应方法的方法体
     *
     * 根据方法形参和返回值的不同组合，lambda表达式可以分成以下几类：
     * <p>1、没有形参{@link FunctionInterface.AcceptEmpty#accept()}：
     * <pre>{@code
     *      () -> {
     *          // 方法体
     *      }
     * }</pre>
     * <p>2、一个形参{@link FunctionInterface.AcceptOne#accept(Object)}：
     * <pre>{@code
     *      (t) -> {
     *          // 方法体
     *      }
     * }</pre>
     * <p>3、多个形参{@link FunctionInterface.AcceptMore#accept(Object, Object)}：
     * <pre>{@code
     *      (t1, t2[, ……]) -> {
     *          // 方法体
     *      }
     * }</pre>
     * <p>4、没有返回值{@link FunctionInterface.ReturnVoid#returnVoid()}：
     * <pre>{@code
     *      () -> {
     *          // 方法体
     *      }
     * }</pre>
     * <p>5、有返回值{@link FunctionInterface.ReturnR#returnR()}：-> R
     * <pre>{@code
     *      () -> {
     *          // 方法体
     *          return something;
     *      }
     * }</pre>
     * <p>
     * 根据形参个数的不同，形参部分可以有不同的写法：
     * <p>1、没有形参或多个形参（超过1个），需要带()：
     * <pre>{@code
     *     () -> {
     *
     *     }
     *     (t1, t2[, ……]) {
     *
     *     }
     * }</pre>
     * <p>2、一个形参，可以省略()：
     * <pre>{@code
     *      (t) -> {
     *
     *      }
     *      t -> {
     *
     *      }
     * }</pre>
     * <p>
     * 根据方法体中代码行数的不同，方法体部分也有不同的写法：
     * <p>1、一行代码，可以省略{}（此时该行代码的“return”和“;”也必须省略）：
     * <pre>{@code
     *      () -> System.out.println("Hello World!")
     *      () -> {
     *          System.out.println("Hello World!");
     *      }
     *      () -> "Hello World!"
     *      () -> {
     *          return "Hello World!"
     *      }
     * }</pre>
     * <p>2、多行代码，不可以省略{}：
     * <pre>{@code
     *      () -> {
     *          System.out.println("Hello");
     *          System.out.println("World!");
     *      }
     * }</pre>
     */
    static void demo02() {
        // 创建service实例，规定了形参和返回值类型
        Service<Integer, Integer, String> service = new Service<>(1, 2);
        // 1、没有形参
        service.acceptEmpty(new FunctionInterface.AcceptEmpty() {
            @Override
            public void accept() {
                System.out.println("没有形参");
            }
        });
        service.acceptEmpty(() -> {
            System.out.println("没有形参");
        });
        service.acceptEmpty(() -> System.out.println("没有形参"));
        // 2、一个形参
        service.acceptOne(new FunctionInterface.AcceptOne<Integer>() {
            @Override
            public void accept(Integer t) {
                System.out.println(t);
            }
        });
        service.acceptOne((t) -> System.out.println(t));
        service.acceptOne(t -> System.out.println(t));
        // 3、多个形参
        service.acceptMore(new FunctionInterface.AcceptMore<Integer, Integer>() {
            @Override
            public void accept(Integer t, Integer e) {
                System.out.println(t);
                System.out.println(e);
            }
        });
        service.acceptMore((t, e) -> {
            System.out.println(t);
            System.out.println(e);
        });
        // 4、没有返回值
        service.returnVoid(new FunctionInterface.ReturnVoid() {
            @Override
            public void returnVoid() {
                System.out.println("没有返回值");
            }
        });
        service.returnVoid(() -> System.out.println("没有返回值"));
        // 5、有返回值
        service.returnR(new FunctionInterface.ReturnR<String>() {
            @Override
            public String returnR() {
                return "3";
            }
        });
        service.returnR(() -> "3");
    }

    /**
     * 函数式接口，详细查看{@link FunctionalInterface}
     */
    static class FunctionInterface {
        interface AcceptEmpty {
            void accept();
        }

        interface AcceptOne<T> {
            void accept(T t);
        }

        interface AcceptMore<T, E> {
            void accept(T t, E e);
        }

        interface ReturnVoid {
            void returnVoid();
        }

        interface ReturnR<R> {
            R returnR();
        }
    }

    /**
     * 调用函数式接口的服务类
     * @param <T> 第一个形参类型
     * @param <E> 第二个形参类型
     * @param <R> 返回值类型
     */
    static class Service<T, E, R> {
        private T t;
        private E e;

        public Service(T t, E e) {
            this.t = t;
            this.e = e;
        }

        void acceptEmpty(FunctionInterface.AcceptEmpty acceptEmpty) {
            acceptEmpty.accept();
        }

        void acceptOne(FunctionInterface.AcceptOne<T> acceptOne) {
            acceptOne.accept(this.t);
        }

        void acceptMore(FunctionInterface.AcceptMore<T, E> acceptMore) {
            acceptMore.accept(this.t, this.e);
        }

        void returnVoid(FunctionInterface.ReturnVoid returnVoid) {
            returnVoid.returnVoid();
        }

        R returnR(FunctionInterface.ReturnR<R> returnR) {
            return returnR.returnR();
        }
    }

    /**
     * <p>lambda表达式形式上看起来很像是函数式编程：将一个函数当作形参传给方法。
     * <p>实际上，lambda表达式只是Java的一个语法糖，它本质上仍然是一个普通的Java对象。
     * <p>在执行的过程中，lambda表达式最终还是会被解析成匿名的接口实现类对象。
     * <p>由于多态特性，在执行过程中，调用是外部传进来的实现类实例的代码。
     * <p>在这个过程中，我们甚至可以将该匿名对象保存起来，便于后续多次调用。
     */
    static void demo03() {
        FakeFunctionalProgramming<String, String> ffp = new FakeFunctionalProgramming<>();
        ffp.setT("Xianhuii");
        ffp.setLambda((t) -> "Hello " + t + "!");
        ffp.doSomeThing();  // Hello Xianhuii!
    }

    static class FakeFunctionalProgramming<T, R> {
        private T t;
        private Lambda<T, R> lambda;

        public void setT(T t) {
            this.t = t;
        }

        public void setLambda(Lambda<T, R> lambda) {
            this.lambda = lambda;
        }

        public void doSomeThing() {
            T t = before();
            R r = lambda.method(t);
            after(r);
        }

        public T before() {
            return t;
        }
        public void after(R r) {
            System.out.println(r);
        }
    }
    interface Lambda<T, R> {
        R method(T t);
    }

    static void demo04() {
        LambdaExpression lambdaObjPrinter = new LambdaExpression();
        // 案例1
//        lambdaObjPrinter.printConsumer(o -> o.getClass());
//        lambdaObjPrinter.printConsumer(o -> o.getClass());
        // 案例2
        for (int i = 0; i < 2; i++) {
            lambdaObjPrinter.printConsumer(o -> o.getClass());
        }
        System.out.println("=============");
        for (int i = 0; i < 2; i++) {
            lambdaObjPrinter.printConsumer(o -> o.getClass());
        }
        System.out.println("=============");
        int index = 0;
        while (index < 2) {
            lambdaObjPrinter.printConsumer(o -> o.getClass());
            index++;
        }
    }
    public void printConsumer(Consumer consumer) {
        System.out.println(consumer.getClass());
        System.out.println(consumer.getClass().getInterfaces()[0]);
        System.out.println(consumer);
    }

    public void printFunction(Function function) {
        System.out.println(function.getClass());
    }

    public static void main(String[] args) {
//        demo01();
//        demo03();
        demo04();
    }
}
