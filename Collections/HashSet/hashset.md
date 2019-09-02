# HashSet 简介

HashSet 是 Java 集合框架的一员，用于存放不重复的集合元素。关于 HashSet 有如下几个要点。

- HashSet 存放唯一不重复元素，允许存放一个`null`值。

- HashSet 内部使用 HashMap 实现。

- HashSet 不保证元素插入顺序，如需保证元素插入顺序，可以使用`LinkedHashSet`。

- HashSet 不保证元素排列顺序，如需保证元素排列顺序，可以使用`TreeSet`。

- HashSet 不是线程安全的容器，如需保证线程安全，可以使用`Collections.synchronizedSet`或`CopyOnWriteArraySet`。

# HashSet 继承体系

如图所示，HashSet 继承自`AbstractSet`抽象父类，实现了`Set`接口。

![Collections-HashSet-1-Hierachy][Collections-HashSet-1-Hierachy]

> 图：Java HashSet 继承体系

# HashSet 基本用法

以下代码展示了 HashSet 基本使用方式。

```java
import java.util.HashSet;
import java.util.Iterator;
public class HashSetTest {
    public static void main(String[] args) {
        /* 创建 HashSet 容器 */
        HashSet<Integer> hashSet = new HashSet<Integer>();
        /* 查看容器容量 */
        System.out.println(hashSet.size()); // 0
        /* 向 HashSet 添加元素 */
        for (int i = 0; i < 10; ++i) {
            hashSet.add(i);
        }
        /* 获取容器容量 */
        System.out.println(hashSet.size()); // 10
        /* 无法加入重复元素 */
        hashSet.add(0);
        System.out.println(hashSet.size()); // 10
        /* 可以加入一个 null 值 */
        hashSet.add(null);
        System.out.println(hashSet.size()); // 11
        /* 迭代遍历容器元素 */
        for (Iterator<Integer> iterator = hashSet.iterator();
            iterator.hasNext(); /* Nope */ ) {
            System.out.println(iterator.next());
        }
    }
}
/* EOF */
```
> 代码清单：HashSet 基本用法

# HashSet 源码剖析

从 JDK 源码角度分析 HashSet。**HashSet 内部使用 HashMap 实现，利用 HashMap 键不能重复的特性实现`Set`语义，大部分接口实现都重用 HashMap 代码**。

## HashSet 数据字段

观察 HashSet 类中的数据字段，可以得知：

- HashSet 内部使用 HashMap 实现。

- 利用了 HashMap 键不重复的特性。

- 内部 HashMap 关联的值是一个空对象。

```java
public class HashSet<E>
    extends AbstractSet<E>
    implements Set<E>, Cloneable, java.io.Serializable
{
    /* 序列化号 */
    static final long serialVersionUID = -5024744406713321676L;

    /* 使用 HashMap */
    private transient HashMap<E,Object> map;

    /* Dummy 对象，用作内部 HashMap 的值 */
    private static final Object PRESENT = new Object();
}
```
> 代码清单：HashSet 数据字段

## HashSet 构造函数

HashSet 构造函数与 HashMap 一致。

```java
public HashSet() {
    map = new HashMap<>();
}
public HashSet(int initialCapacity) {
    map = new HashMap<>(initialCapacity);
}
public HashSet(int initialCapacity, float loadFactor) {
    map = new HashMap<>(initialCapacity, loadFactor);
}
/* 未开放的构造函数 */
HashSet(int initialCapacity, float loadFactor, boolean dummy) {
    map = new LinkedHashMap<>(initialCapacity, loadFactor);
}
public HashSet(Collection<? extends E> c) {
    map = new HashMap<>(Math.max((int) (c.size()/.75f) + 1, 16));
    addAll(c);
}
```
> 代码清单：HashSet 构造函数

## HashSet 其他方法

HashSet 其他方法基本都重用 HashMap 。

```java
public boolean add(E e) {
    return map.put(e, PRESENT) == null;
}
public void clear() {
    map.clear();
}
public boolean contains(Object o) {
    return map.containsKey(o);
}
public boolean isEmpty() {
    return map.isEmpty();
}
public int size() {
    return map.size();
}
public Iterator<E> iterator() {
    return map.keySet().iterator();
}
```
> 代码清单：HashSet 其他方法

[Collections-HashSet-1-Hierachy]: ../../images/Collections-HashSet-1-Hierachy.png

<!-- EOF -->
