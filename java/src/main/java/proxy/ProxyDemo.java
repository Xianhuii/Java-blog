package proxy;

import java.lang.reflect.*;

public class ProxyDemo {
    public interface IT1 {
        void fun1();
    }
    public interface IT2 {
        Object fun2(Object[] args);
    }
    public static class Real implements IT1, IT2 {

        @Override
        public void fun1() {
            System.out.println("real fun1");
        }

        @Override
        public Object fun2(Object[] args) {
            String rs = "real fun2";
            System.out.println(rs);
            return rs;
        }
    }

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
        Real real = new Real();
        Object proxyInstance = Proxy.newProxyInstance(ProxyDemo.class.getClassLoader(),
                new Class[]{IT1.class, IT2.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("proxy method: " + method.getName());
                        return method.invoke(real, args);
                    }
                });
        // 获取当前代理类的属性
        System.out.println("代理类成员变量：");
        Field[] fields = proxyInstance.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            StringBuilder sb = new StringBuilder();
            Field field = fields[i];
            // 修饰符
            int modifiers = field.getModifiers();
            sb.append(Modifier.toString(modifiers)).append(" ");
            field.setAccessible(true);
            // 成员变量类型&变量名
            sb.append(field.getType().getName()).append(" ").append(field.getName()).append("：");
            Method m = (Method) field.get(Method.class);
            m.setAccessible(true);
            // 方法名
            sb.append(m.getName());
            System.out.println(sb.toString());
        }
        System.out.println("Proxy类成员变量：");
        Field[] superFields = proxyInstance.getClass().getSuperclass().getDeclaredFields();
        for (int i = 0; i < superFields.length; i++) {
            StringBuilder sb = new StringBuilder();
            Field field = superFields[i];
            // 修饰符
            int modifiers = field.getModifiers();
            sb.append(Modifier.toString(modifiers)).append(" ");
            field.setAccessible(true);
            // 成员变量类型&变量名
            sb.append(field.getType().getName()).append(" ").append(field.getName());
            System.out.println(sb.toString());
        }
        System.out.println("代理类方法:");
        Method[] methods = proxyInstance.getClass().getMethods();
        for (int i = 0; i < methods.length; i++) {
            StringBuilder sb = new StringBuilder();
            Method method = methods[i];
            int modifiers = method.getModifiers();
            sb.append(Modifier.toString(modifiers)).append(" ");
            sb.append(method.getReturnType().getName()).append(" ").append(method.getName());
            System.out.println(sb.toString());
        }
        System.out.println("代理类构造函数:");
        Constructor<?>[] constructors = proxyInstance.getClass().getConstructors();
        for (int i = 0; i < constructors.length; i++) {
            StringBuilder sb = new StringBuilder();
            Constructor constructor = constructors[i];
            int modifiers = constructor.getModifiers();
            sb.append(Modifier.toString(modifiers)).append(" ");
            sb.append(constructor.getName()).append("(");
            Class[] parameterTypes = constructor.getParameterTypes();
            for (int i1 = 0; i1 < parameterTypes.length; i1++) {
                sb.append(parameterTypes[i1].getName());
                if (i1 < parameterTypes.length - 1) {
                    sb.append(",");
                }
            }
            sb.append(")");
            System.out.println(sb.toString());
        }
    }
}
