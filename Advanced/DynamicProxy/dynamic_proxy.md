# Java 动态代理

![Advanced-DynamicProxy-1][Advanced-DynamicProxy-1]

> 图：Java 代理执行逻辑

# 代理模式简介

代理模式是常用的设计模式之一，其核心思想是：**对实现类的替代，对方法的增强**。

![Advanced-DynamicProxy-2][Advanced-DynamicProxy-2]

> 图：Java 代理模式 UML 示意图

需要注意的有下面几点：

- 用户只关心接口功能，而不在乎谁提供了功能。

- 接口真正实现者不与用户直接接触，而是通过代理。

- 代理也实现了相应接口，能够直接与用户接触。

- 用户调用代理类时，代理类内部调用了具体实现类的方法，所以代理类是中介者，可以增强方法操作。

- 代理可以分为「静态代理」和「动态代理」两种。

# 无代理

先来看一下不使用代理的情况：抽象接口 -> 实现接口 -> 主类调用。

```java
public interface Subject {
    public void doSomething();
}
```
> 代码清单：抽象接口

```java
public class SubjectImpl implements Subject {
    @Override
    public void doSomething() {
        System.out.println("call doSomething()");
    }
}
```
> 代码清单：接口实现类

```java
public class SubjectTest {
    public static void main(String[] args) {
        Subject sub = new SubjectImpl();
        sub.doSomething();
    }
}
```
> 代码清单：主类

如需额外添加功能，则要修改接口实现类。如此一来，通用功能代码与具体实现代码混合，逻辑不清。

```java
public class SubjectImpl implements Subject {
    @Override
    public void doSomething() {
        before();
        System.out.println("call doSomething()");
        after();
    }
    public void before() {
        System.out.println("call before()");
    }
    public void after() {
        System.out.println("call after()");
    }
}
```
> 代码清单：接口实现类（添加通用功能）

# 静态代理

使用静态代理模式优化上述逻辑：创建静态代理类，将通用功能代码与具体实现代码分离。

```java
public class SubjectProxy implements Subject {
    /* 具体实现类 */
    private Subject subject;
    public SubjectProxy() {
        subject = new SubjectImpl();
    }
    @Override
    public void doSomething() {
        before();
        subject.doSomething();
        after();
    }
    private void before() {
        System.out.println("call before()");
    }
    private void after() {
        System.out.println("call after()");
    }
}
```
> 代码清单：静态代理类

```java
public class SubjectTest {
    public static void main(String[] args) {
        Subject sub = new SubjectProxy();
        sub.doSomething();
    }
}
```
> 代码清单：主类

静态代理带来了一定的灵活性，在不改变被代理接口实现类源代码的情况下，增强类方法，只需调用端少量修改调用代码即可。

静态代理的缺陷在于：通用功能代码还是不方便复用，需要写大量的代理包装类，代码冗余。

# JDK 动态代理

JDK 动态代理需要一个实现`InvocationHandler`接口的通用代理类，并通过`Proxy`类动态生成出实际代理类，使用`invoke()`反射调用方法。

```java
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.lang.reflect.Method;
public class ProxyHandler implements InvocationHandler {
    /* 通用实现类 */
    private Object target;
    /* 绑定通用实现类 */
    public Object bind(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(
            /* 代理类类加载器 */
            this.target.getClass().getClassLoader(),
            /* 代理类实现接口 */
            this.target.getClass().getInterfaces(),
            /* 调用转发类 */
            this
        );
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
    throws Throwable {
        before();
        /* 反射调用 */
        Object result = method.invoke(this.target, args);
        after();
        return result;
    }
    private void before() {
        System.out.println("call before()");
    }
    private void after() {
        System.out.println("call after()");
    }
}
```
> 代码清单：JDK 动态代理

```java
public class SubjectTest {
    public static void main(String[] args) {
        Subject sub = new ProxyHandler().bind(new SubjectImpl());
        sub.doSomething();
    }
}
```
> 代码清单：主类

动态代理十分灵活，对于绑定的任意接口实现类，都可以动态生成一个具体的代理类，无需用户手动创建。

可以看到，JDK 动态代理也需要被代理方法实现某一接口，JDK 动态代理根据接口信息拦截特定的方法，如果方法没有实现任何接口，JDK 动态代理也就无能为力了。

# CGLib 动态代理

`cglib`（Byte Code Generation Library）是一个 Java 动态字节码生成库，提供高阶 API 方便动态生成 Java 字节码，底层使用了小而快的字节码处理框架`ASM`。利用`cglib`，可以实现不依赖接口的动态代理。

> `cglib`开源地址：https://github.com/cglib/cglib

```java
/* 无实现接口 */
public class SubjectImpl {
    public void doSomething() {
        System.out.println("call doSomething()");
    }
}
```
> 代码清单：代理类

```java
import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
public class CGLibProxy implements MenthodInterceptor {
    private CGLibProxy() {}
    /* 单例模式 */
    private static CGLibProxy instance = new CGLibProxy();
    public static CGLibProxy getInstance() {
        return instance;
    }
    /* 取得动态代理（泛型） */
    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> clazz) {
        return (T)Enhancer.create(clazz, this);
    }
    /* 方法拦截 */
    @Override
    public Object intercept(
        Object obj,
        Method method,
        Object[] args,
        MethodProxy proxy
    ) throws Throwable {
        before();
        Object result = proxy.invokeSuper(obj, args);
        after();
        return result;
    }
    private void before() {
        System.out.println("call before()");
    }
    private void after() {
        System.out.println("call after()");
    }
}
```
> 代码清单：`cglib`动态代理

```java
public class SubjectTest {
    public static void main(String[] args) {
        SubjectImpl sub = CGLibProxy.getInstance()
                                    .getProxy(SubjectImpl.class);
        sub.doSomething();
    }
}
```
> 代码清单：主类

可以看到，`cglib`实现动态代理不需要接口信息。但是正因为没有接口信息，`cglib`动态代理没有办法拦截指定方法，被代理类中定义的所有方法都被`cglib`拦截包装。

# 总结

静态代理、JDK 动态代理、`cglib`动态代理这三种代理方式各有特点。

- 静态代理与 JDK 动态代理都需要被代理类实现接口，通过接口信息对特定方法进行拦截包装。

- CGLib 动态代理虽然不需要接口信息，但是其会对被代理类的所有方法都进行拦截包装。另外，因其实现方式是继承，所以不能代理`final`方法。

[Advanced-DynamicProxy-1]: ../../images/Advanced-DynamicProxy-1.jpg

[Advanced-DynamicProxy-2]: ../../images/Advanced-DynamicProxy-2.jpg

<!-- EOF -->
