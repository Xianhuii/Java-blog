# 0 捕获自由变量

为了使`lambda`表达式看起来更像是函数式编程，可以在函数内部直接访问外部变量。`lambda`表达式具有一个特殊的能力，它可以捕获自由变量。

自由变量指的是`lambda`表达式外（之前）的变量，如：

```java
String str = "Hello, lambda!";
() -> {
    System.out.println(str);
}
```

这里的`str`就是自由变量。

其实，匿名对象也有该功能：

```java
String str = "Hello, lambda!";
FI2.fun(new FI() {
    @Override
    public void run() {
        System.out.println(str);
    }
});
```

`lambda`表达式只能够捕获某些特殊的自由变量：不可变变量（`final`变量或隐式`final`变量）。

在某种意义上，我们可以认为`lambda`表达式只能捕获被`final`修饰的自由变量。由于`String`本身就是不可变的，这里我们换个可变的来举例子：

```java
final int a = 1;
() -> {
    System.out.println(a);
}
```

这意味着，该变量在声明之后，就不可以再重新赋值了。如果是基本数据类型，表示变量值不能再改变；如果是引用数据类型，表示引用的对象地址不能再改变。

通常，我们会省略`final`修饰符，即该变量为隐式`final`变量：

```java
int a = 1;
() -> {
    System.out.println(a);
}
```

编译器会帮我们检查：

- 如果该变量被`lambda`表达式捕获，那么它就不能被重新赋值（不可变变量）。
- 如果该变量被重新，那么它就不能被`lambda`表达式捕获（可变变量）。

需要注意的是，被`lambda`表达式捕获的自由变量只能初始化一次。即便它是隐式`final`变量，在`lambda`表达式之前/之内/之后，都不能重新赋值：

```java
int a = 1;
// a = 2; 报错
() -> {
    // a = 2; 报错
    System.out.println(a);
}
// a = 2; 报错
```

# 1 作用域

由于`lambda`表达式捕获自由变量的特殊能力，使得它具有十分特殊的作用域。

一方面，`lambda`表达式内部可以访问到之前的不可变自由变量；另一方面，`lambda`表达式之后不能访问到其内部定义的变量：

```java
int a = 1;
() -> {
    System.out.println(a);	// 可以访问
    int b = 2;
}
// System.out.println(b); 报错
```

这让我们联想到其他编程语言，如`JavaScript`。不同的是，它的自由变量是可变的：

```javascript
let a = 1;
function fun() {
    console.log(a);
    let b =2;
}
fun();	// 1
b;	// VM273:1 Uncaught ReferenceError: b is not defined
a = 3;
fun();	// 3
```

`lambda`表达式本质上是动态创建的匿名对象，其方法体为对象实例的方法体。而此时在编译器的帮助下竟然可以在对象内部访问到外部的变量。这对以往的思维可能有很大的颠覆。

# 2 `this/super`

由于`lambda`表达式捕获自由变量的特殊能力，它总能够捕获外部的`this`和`super`。

因此，在`lambda`表达式中使用`this`或`super`关键字时，指的是创建这个`lambda`表达式所在的方法中的`this`或`super`对象。

```java
public class LambdaScope {
    static interface FI {
        void run();
    }
    static class FI2 {
        static void fun(FI fi) {
            fi.run();
        }
    }
    public void test() {
        FI2.fun(() -> {
            // class lambda.LambdaScope
            System.out.println(this);
            // class lambda.LambdaScope
            System.out.println(super.getClass());
        });
    }
}
```

这里的`this`和`super`指的是`test()`方法中的`this`和`super`。

# 3 可变自由变量

在某些情况下，`lambda`表达式中既需要获取外部的自由变量，同时又要求该变量可以重新赋值。

这种情况下可以将该自由变量提升为全局唯一的变量，如静态变量。

```java
public class LambdaScope {
    public static int count = 0;
    static interface FI {
        void run();
    }
    static class FI2 {
        static void fun(FI fi) {
            fi.run();
        }
    }
    public static void demo05() {
        FI2.fun(() -> {
            LambdaScope.count++;
        });
    }
}
```

但是，此时需要考虑并发执行多个`lambda`表达式时的不安全问题。