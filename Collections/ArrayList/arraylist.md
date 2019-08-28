# ArrayList 简介

ArrayList 是 Java 实现的动态数组，用于存放顺序对象，是最常用的 Java 集合类之一。ArrayList 并不是线程安全的集合容器。

# ArrayList 继承关系

从 ArrayList 继承关系图中可以看出，`ArrayList`继承自抽象父类`AbstractList`，实现了`List`接口。另外，ArrayList 还支持快速随机访问`RandomAccess`。

![Collections-ArrayList-1-Hierarchy][Collections-ArrayList-1-Hierarchy]

> 图：ArrayList 继承关系图

# ArrayList 数据结构

ArrayList 底层数据结构比较简单：对象数组。数组具有的特性（固定类型、固定长度、随机访问等），ArrayList 也都具备，另外 ArrayList 也扩展了数组，可以动态伸缩。

![Collections-ArrayList-2-DataStructure][Collections-ArrayList-2-DataStructure]

> 图：ArrayList 数据结构

# ArrayList 源码剖析

从 JDK 源码角度深入分析 ArrayList 实现。

## ArrayList 数据字段

观察 ArrayList 类中的数据字段，可以得知：

- 底层数据结构为**对象数组**

- 默认数组容量为`10`

- 通过整型变量`size`纪录实际容器实际存储元素数量

```java
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
{
    /* 序列化号 */
    private static final long serialVersionUID = 8683452581122892189L;

    /* 默认初始化容量 */
    private static final int DEFAULT_CAPACITY = 10;

    /* 空对象数组实例 */
    private static final Object[] EMPTY_ELEMENTDATA = {};

    /* 默认空对象数组实例 */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /* 实际存储数据的对象数组 */
    transient Object[] elementData; // non-private to simplify nested class access

    /* 数组实际存储元素的数量 */
    private int size;
}
```
> 代码清单：ArrayList 数据字段

## ArrayList 构造函数

观察 ArrayList 类的构造函数，可以看到，ArrayList 支持三种构造方式。

- 无参数默认构造：构造以默认容量`10`构造空对象数组。

- 指定容量构造：构造指定容量的空对象数组。

- 以`Collection`序列对象构造：构造顺序为迭代器返回元素顺序的对象数组。

```java
/**
 * 以指定容量构造。
 *
 * @param  initialCapacity  初始化容量
 * @throws IllegalArgumentException 如果初始化容量为负
 */
public ArrayList(int initialCapacity) {
    /* 初始化容量大于0 : 构造指定容量的空数组
       初始化容量等于0 : 构造容量为0的空数组
       初始化容量小于0 : 抛出异常 */
    if (initialCapacity > 0) {
        this.elementData = new Object[initialCapacity];
    } else if (initialCapacity == 0) {
        this.elementData = EMPTY_ELEMENTDATA;
    } else {
        throw new IllegalArgumentException("Illegal Capacity: "+
                                           initialCapacity);
    }
}
/**
 * 以默认容量 10 构造。
 */
public ArrayList() {
    this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
}
/**
 * 以 Collection 序列对象构造，顺序为迭代器返回元素顺序。
 *
 * @param c 序列对象
 * @throws NullPointerException 如果序列对象为空
 */
public ArrayList(Collection<? extends E> c) {
    /* 序列对象转换数组 */
    elementData = c.toArray();
    /*  设置容量 */
    if ((size = elementData.length) != 0) {
        /* Collection.toArray() 有可能返回不正常数据 */
        if (elementData.getClass() != Object[].class) {
            /* 拷贝元素 */
            elementData = Arrays.copyOf(elementData, size, Object[].class);
        }
    } else {
        /* 序列对象长度为零则切换至空数组 */
        this.elementData = EMPTY_ELEMENTDATA;
    }
}
```
> 代码清单：ArrayList 构造函数



[Collections-ArrayList-1-Hierarchy]: ../../images/Collections-ArrayList-1-Hierarchy.png

[Collections-ArrayList-2-DataStructure]: ../../images/Collections-ArrayList-2-DataStructure.png

<!-- EOF -->
