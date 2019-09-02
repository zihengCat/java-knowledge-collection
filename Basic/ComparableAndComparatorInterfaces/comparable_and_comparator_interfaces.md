# Java `Comparable` 与 `Comparator` 接口

Java 语言并没有类似 C++ 语言的运算符重载（Operator Overloading）特性。为了简单易懂，Java 没有选择隐式的运算符重载机制，而是使用了接口与对象方法。

对象之间的比较，是十分常见的操作，筛选排序都会用到。**Java 定义了`Comparable`与`Comparator`两枚接口，用于对象比较操作**。

# Java `Comparable` 接口

Java `Comparable` 接口用于用户自定义对象之间比较操作。该接口位于`java.lang`包下，接口只包含一个简单的`compareTo()`方法。

```java
public interface Comparable<T> {
    public int compareTo(T o);
}
```
> 代码清单：`Comparable`接口源码

`CompareTo()`方法接受一个指定对象，返回一个整型值，根据返回的整型值比较当前对象与目标对象的大小。

- `-`：返回负值，小于。

- `0`：返回零值，等于。

- `+`：返回正值，大于。

通过一个样例来理解`Comparable`接口。

```java
import java.lang.Comparable;
public class ComparableTest {
    public static void main(String[] args) {
        UserDefinedClass u1 = new UserDefinedClass("u1", 10);
        UserDefinedClass u2 = new UserDefinedClass("u2", 20);
        System.out.printf(
            "[INFO]: u1.compareTo(u2) -> %d" +
            Systems.lineSeparator(),
            u1.compareTo(u2)
        );
    }
}
class UserDefinedClass implements Comparable<UserDefinedClass> {
    private String name;
    private Integer age;
    public UserDefinedClass(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
    @Override
    public int compareTo(UserDefinedClass o) {
        if (this.age > o.age) {
            return 1;
        } else if (this.age < o.age) {
            return -1;
        } else if (this.age == o.age) {
            return 0;
        } else {
            throw new RuntimeException();
        }
    }
}
/* EOF */
```
> 代码清单：`Comparable`接口样例

# Java `Comparator` 接口

Java `Comparator`接口也用于用户自定义对象之间比较操作。与`Comparable`接口不同，`Comparator`接口不需要由自定义类自身去实现，而是交给第三方类实现。因此，`Comparator`接口实现类也被称为“比较器”。

接口位于`java.util`包下，接口包含一个简单的`compare()`方法。该方法接受两个指定类型对象，返回一个整型值，根据返回的整型值比较传入对象之间的大小。

```java
public interface Comparator<T> {
    int compare(T o1, T o2);
}
```
> 代码清单：`Comparator`接口源码

通过一个样例来理解`Comparator`接口。

```java
import java.util.Comparator;
public class ComparatorTest {
    public static void main(String[] args) {
        System.out.printf(
            "[INFO]: UserDefinedComparator(o1, o2) -> %d" +
            System.lineSeparator(),
            new UserDefinedComparator().compare(
                new UserDefinedClass("u1", 11),
                new UserDefinedClass("u1", 11)
            )
        ); // [INFO]: UserDefinedComparator(o1, o2) -> 0
    }
}
class UserDefinedComparator implements Comparator<UserDefinedClass> {
    @Override
    public int compare(UserDefinedClass o1, UserDefinedClass o2) {
        if (o1.getAge() > o2.getAge()) {
            return 1;
        } else if (o1.getAge() < o2.getAge()) {
            return -1;
        } else if (o1.getAge() == o2.getAge()) {
            return 0;
        } else {
            throw new RuntimeException();
        }
    }
}
class UserDefinedClass {
    private String name;
    private Integer age;
    public UserDefinedClass(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
    public getName() {
        return name;
    }
    public getAge() {
        return age;
    }
}
/* EOF */
```
> 代码清单：`Comparator`接口样例

# `Comparable` 与 `Comparator` 接口的异同点

总结一下`Comparable`与`Comparator`接口的异同点，具体应用哪个接口需要根据实际使用场景而定。

同：

- 类型相同：都是接口类型。

- 作用相同：都是实现自定义对象之间的比较操作的手段。

异：

- 实现方式不同：
    - `Comparable`由自定义类自身实现。
    - `Comparator`由第三方比较器类实现。

- 存放路径不同：
    - `Comparable`位于`java.lang`包下。
    - `Comparator`位于`java.util`包下。

<!-- EOF -->
