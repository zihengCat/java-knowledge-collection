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

...





[Collections-ArrayList-1-Hierarchy]: ../../images/Collections-ArrayList-1-Hierarchy.png

[Collections-ArrayList-2-DataStructure]: ../../images/Collections-ArrayList-2-DataStructure.png


<!-- EOF -->
