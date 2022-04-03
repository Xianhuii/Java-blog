package lambda;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 函数式接口
 * <ol>
 *     <li>概念：{@link Concept}
 *     <li>示例：{@link Demo}
 *     <li>函数式接口中default方法的应用：{@link FunctionalInterface#defaultMethod()}
 *     <li>@{@link java.lang.FunctionalInterface}注解：{@link Annotation}
 *     <li>内置函数式接口：{@link java.util.function}
 * </ol>
 * @author Xianhuii
 * @date 2022/3/20 12:54
 */
public class FunctionalInterface {

    /**
     * Java语言规范中，定义了函数式接口的概念：
     * <blockquote>A functional interface is an interface that has just one abstract method (aside
     * from the methods of Object), and thus represents a single function contract.</blockquote>
     * 简单来说，函数式接口必须满足以下条件：
     * <ol>
     *     <li>是一个接口。
     *     <li>接口中只有一个抽象方法。
     *     <li>该抽象方法不能是{@link Object}中public的方法。
     * </ol>
     * JVM能够自动识别Object中声明为public的方法，并将其过滤。从而可以在函数式接口中找到正确的自定义的抽象方法。
     */
    interface Concept {
    }

    /**
     * 函数式接口示例：
     * <ol>
     *     <li>接口中只有一个自定义抽象方法：{@link FI1}
     *     <li>接口中有一个自定义抽象方法，同时存在一个或多个Object中public抽象方法：{@link FI2}
     *     <li>接口中有一个Object中非public抽象方法：{@link FI3}
     *     <li>接口中有一个非Object抽象方法，同时存在一个或多个private/default方法：{@link FI4}
     *     <li>接口满足函数式接口条件，尽管继承了非函数式接口，其本身仍然是函数式接口：{@link FI5}
     * </ol>
     *
     * 不是函数式接口示例：
     * <ol>
     *     <li>接口中有多个自定义抽象方法：{@link NoFI1}。
     *     <li>接口中只有一个或多个Object中public的抽象方法->没有自定义抽象方法：{@link NoFI2}。
     *     <li>接口中存在多个Object中protected方法：{@link NoFI3}
     *     <li>接口中有一个非Object抽象方法，同时存在一个或多个private/default方法：{@link NoFI4}
     * </ol>
     *
     * 其实，判断接口是否为函数式接口，只需要看该接口是否只有一个自定义抽象方法。
     * 理论上，该抽象方法也可以是继承自Object中的protected方法：clone()或finalize()，但是实际没有人会这么定义。
     */
    interface Demo {}

    /**
     * 函数式接口：接口中只有一个抽象方法，并且该方法不是Object中的方法，是标准的函数式接口。
     */
    interface FI1 {
        void method();
    }

    /**
     * 函数式接口：接口中只有一个自定义抽象方法，同时存在一个或多个Object抽象方法。
     * 由于JVM能够识别并排除Object中的方法，因此能够确认该接口中只有一个自定义的抽象方法。
     */
    interface FI2 {
        void method();
        String toString();
        boolean equals(Object obj);
        // 其他Object中的public方法
    }

    /**
     * 函数式接口：接口中只有一个Object非public抽象方法。
     * 由于clone()是protected方法，JVM不能过滤，会将它当作接口中自定义的抽象方法。
     */
    interface FI3 {
        Object clone();
    }
    /**
     * 函数式接口：接口中有一个非Object抽象方法，同时存在一个或多个private/default方法。
     * 由于private/default方法并不是抽象方法，所以JVM在解析lambda表达式时也能排除这些方法。
     */
    interface FI4 {
        void method();
        default void pMethod() {
            System.out.println("Hello, Functional Interface!");
        }
        // 其他default或private方法
    }

    /**
     * 函数式接口：FI5自身满足函数式接口条件，虽然NoFI1不是函数式接口，但是FI5自身仍然式函数式接口。
     */
    interface FI5 extends NoFI1 {
        void method();
    }

    /**
     * 不是函数式接口：接口中有多个自定义抽象方法。
     */
    interface NoFI1 {
        void method1();
        void method2();
    }

    /**
     * 不是函数式接口：接口中只有一个或多个Object中public的抽象方法：没有自定义抽象方法。
     */
    interface NoFI2 {
        String toString();
        // 其他Object中的public方法
    }

    /**
     * 不是函数式接口：接口中存在多个Object中protected方法。
     */
    interface NoFI3 {
        Object clone();
        void finalize();
    }

    /**
     * 不是函数式接口：接口中存在一个自定义抽象方法，但同时存在一个或多个Object中protected方法。
     */
    interface NoFI4 {
        void method();
        Object clone();
        void finalize();
    }

    /**
     * 函数式接口中，通过定义default/private方法，可以抽取出一些函数式接口公共的功能：记录日志/保存请求数据等。
     * 在内置的函数式接口中，也定义了许多default方法，主要用于增强该接口的功能：如链式调用。
     */
    static void defaultMethod() {
        new ServiceDemo().call(() -> System.out.println("Do something..."));
    }

    interface FIDemo {
        void method();
        default void before() {
            System.out.println(LocalDateTime.now());
        }
        default void after() {
            System.out.println(LocalDateTime.now());
        }
    }
    static class ServiceDemo {
        public void call(FIDemo fiDemo) {
            fiDemo.before();
            fiDemo.method();
            fiDemo.after();
        }
    }

    static class ServiceDemo2 {
        public Object call(FI3 fi3) {
            return fi3.clone();
        }
    }

    public static void protectedMethod() {
        Object call = new ServiceDemo2().call(() -> new Date().clone());
        System.out.println(call);
    }

    public static void main(String[] args) {
//        defaultMethod();
        protectedMethod();
    }

    /**
     * @{@link FunctionalInterface}注解是一个标志性注解。
     * 它的作用是通知编译器帮我们检查所定义的函数式接口是否符合规范。
     * 如果该接口不是函数式接口，那么在编译阶段就会报错，进行提示。
     */
    interface Annotation {

    }

    /**
     * 函数式接口，不会报错
     */
    @java.lang.FunctionalInterface
    interface AnnotatedFI {
        void method();
    }

    /**
     * 非函数式接口，在编译阶段就会报错
     */
//    @java.lang.FunctionalInterface
    interface AnnotatedNoFI {
        void method1();
        void method2();
    }
}
