# Java `LinkedList` 简介

LinkedList 是 JDK 实现的「双向链表」集合容器，用于存储线性数据，是常用的 Java 集合实现类之一。LinkedList 并不是线程安全的集合容器。

# LinkedList 继承体系

如图所示，LinkedList 继承自抽象父类`AbstractSequentialList`，可顺序访问集合元素，实现了`List`，`Deque`接口。因此，LinkedList 还可以被用作「栈」与「队列」。

![Collections-LinkedList-1-Hierarchy][Collections-LinkedList-1-Hierarchy]

> 图：LinkedList 继承体系图

# LinkedList 数据结构

LinkedList 的底层数据结构为「双向链表」，封装元素为链表节点，通过前趋后继节点指针串联数据，使用链式存储，内存不连续。

LinkedList 使用链式存储结构，可以自由扩缩容量，插入删除数据效率高，查找数据效率较低。

![Collections-LinkedList-2-DataStructure][Collections-LinkedList-2-DataStructure]

> 图：LinkedList 数据结构图

# LinkedList 基本用法

以下代码展示了 LinkedList 基本使用方式。

```java
import java.util.List;
import java.util.LinkedList;
public class LinkedListTest {
    public static void main(String[] args) {
        List<Integer> linkedList = new LinkedList<Integer>();
        for (int i = 0; i < 10; ++i) {
            linkedList.add(i);
        }
        System.out.println(linkedList.get(1));
    }
}
/* EOF */
```
> 代码清单：LinkedList 基本用法

# LinkedList 源码剖析

从 JDK 源码角度深入分析 LinkedList 实现。

## LinkedList 数据字段

观察 LinkedList 类中的数据字段，可以得知：

- 底层数据结构为双向链表。

- 封装数据为双向链表节点。

- 使用一个整型变量`size`纪录实际存储元素数量。

```java
public class LinkedList<E>
    extends AbstractSequentialList<E>
    implements List<E>, Deque<E>, Cloneable, java.io.Serializable
{
    /* 序列化号 */
    private static final long serialVersionUID = 876323262645176354L;

    /* 实际存储元素数量 */
    transient int size = 0;

    /* 双向链表头节点 */
    transient Node<E> first;

    /* 双向链表尾节点 */
    transient Node<E> last;

    /* 链表节点数据结构 */
    private static class Node<E> {
        /* 实际数据 */
        E item;
        /* 后继节点指针 */
        Node<E> next;
        /* 前驱节点指针 */
        Node<E> prev;

        /* 构造函数 */
        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
```
> 代码清单：LinkedList 数据字段

## LinkedList 构造函数

LinkedList 支持两种构造方式：

- 无参构造：不做任何事。

- 以另一`Collection`序列容器构造：构造顺序为迭代器返回元素顺序。

```java
/**
 * 无参空构造函数。
 */
public LinkedList() {
}
/**
 * 以另一集合容器构造。
 */
public LinkedList(Collection<? extends E> c) {
    this();
    addAll(c);
}
```
> 代码清单：LinkedList 构造函数

## LinkedList 添加元素方法

LinkedList 既可以向链表头部添加元素，也可以向链表尾部添加元素，还也可以向链表指定位置插入元素。数据结构具体操作交由`linkFirst()`，`linkLast()`，`linkBefore()`等链表操作函数完成。

```java
/* 添加元素 */
public boolean add(E e) {
    linkLast(e);
    return true;
}
/* 向链表指定位置插入元素 */
public void add(int index, E element) {
    /* 边界检查 */
    checkPositionIndex(index);
    if (index == size) {
        linkLast(element);
    } else {
        linkBefore(element, node(index));
    }
}
/* 插入元素至链表头部 */
public void addFirst(E e) {
    linkFirst(e);
}
/* 插入元素至链表尾部 */
public void addLast(E e) {
    linkLast(e);
}
/* 插入元素至链表尾部，返回状态 */
public boolean offer(E e) {
    return add(e);
}
/* 插入元素至链表头部，返回状态 */
public boolean offerFirst(E e) {
    addFirst(e);
    return true;
}
/* 插入元素至链表尾部，返回状态 */
public boolean offerLast(E e) {
    addLast(e);
    return true;
}
```
> 代码清单：LinkedList 插入方法

链接元素的链表操作方法也比较简单。

```java
/**
 * 将元素链接到链表头部。
 */
private void linkFirst(E e) {
    /* 取得链表头节点 */
    final Node<E> f = first;
    /**
     * 构建新节点
     * 数据域：指定元素
     * 前驱节点：null
     * 后继节点：头节点
     */
    final Node<E> newNode = new Node<>(null, e, f);
    /* 将链表头节点设置为新节点 */
    first = newNode;
    /**
     * 原头节点为空：将尾节点置为新节点
     * 原头节点不为空：将前驱节点置为新节点
     */
    if (f == null) {
        last = newNode;
    } else {
        f.prev = newNode;
    }
    /* 容器存储元素数量 + 1 */
    size++;
    /* 容器操作次数 + 1 */
    modCount++;
}
/**
 * 将元素链接到链表尾部。
 */
void linkLast(E e) {
    /* 取得链表尾节点 */
    final Node<E> l = last;
    /**
     * 构建新节点
     * 数据域：指定元素
     * 前驱节点：尾节点
     * 后继节点：null
     */
    final Node<E> newNode = new Node<>(l, e, null);
    /* 将链表尾节点设置为新节点 */
    last = newNode;
    /**
     * 原尾节点为空：将头节点置为新节点
     * 原头节点不为空：将后继节点置为新节点
     */
    if (l == null) {
        first = newNode;
    } else {
        l.next = newNode;
    }
    /* 容器存储元素数量 + 1 */
    size++;
    /* 容器操作次数 + 1 */
    modCount++;
}
/**
 * 将元素链接到指定节点之前。
 */
void linkBefore(E e, Node<E> succ) {
    // assert succ != null;
    /* 取得指定节点前驱节点 */
    final Node<E> pred = succ.prev;
    /**
     * 构建新节点
     * 数据域：指定元素
     * 前驱节点：指定节点前驱节点
     * 后继节点：指定节点前
     */
    final Node<E> newNode = new Node<>(pred, e, succ);
    /* 将指定节点的前驱节点指针指向新节点 */
    succ.prev = newNode;
    /**
     * 指定节点前驱节点为空：将头节点置为新节点
     * 指定节点前驱节点不为空：后继指针指向新节点
     */
    if (pred == null) {
        first = newNode;
    } else {
        pred.next = newNode;
    }
    /* 容器存储元素数量 + 1 */
    size++;
    /* 容器操作次数 + 1 */
    modCount++;
}
```
> 代码清单：LinkedList 链表插入方法

## LinkedList 移除元素方法


...




# LinkedList 总结

...


[Collections-LinkedList-1-Hierarchy]: ../../images/Collections-LinkedList-1-Hierarchy.png

[Collections-LinkedList-2-DataStructure]: ../../images/Collections-LinkedList-2-DataStructure.png

<!-- EOF -->
