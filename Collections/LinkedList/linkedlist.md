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

LinkedList 既可以向链表头部添加元素，也可以向链表尾部添加元素，还可以向链表指定位置插入元素。数据结构具体操作交由`linkFirst()`，`linkLast()`，`linkBefore()`等链表操作函数完成。

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

链接元素的链表操作方法也比较简单，常规双向链表插入操作。

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

LinkedList 既可以移除链表头部元素，也可以移除链表尾部元素，还可以移除链表指定位置元素。数据结构具体操作交由`unlink()`，`unlinkFirst()`，`unlinkLast()`等链表操作函数完成。

```java
/* 移除链表元素，默认移除头节点 */
public E remove() {
    return removeFirst();
}
/* 移除指定位置链表元素 */
public E remove(int index) {
    /* 边界检查 */
    checkElementIndex(index);
    return unlink(node(index));
}
/* 移除指定链表元素 */
public boolean remove(Object o) {
    /* 遍历链表，找到指定元素后，删除目标元素 */
    if (o == null) {
        for (Node<E> x = first; x != null; x = x.next) {
            if (x.item == null) {
                unlink(x);
                return true;
            }
        }
    } else {
        for (Node<E> x = first; x != null; x = x.next) {
            if (o.equals(x.item)) {
                unlink(x);
                return true;
            }
        }
    }
    /**
     * 返回状态：
     * true: 成功删除目标元素
     * false: 未找到目标元素
     */
    return false;
}
/* 移除链表头节点，头节点为空则抛出异常 */
public E removeFirst() {
    final Node<E> f = first;
    if (f == null) {
        throw new NoSuchElementException();
    }
    return unlinkFirst(f);
}
/* 移除链表尾节点，尾节点为空则抛出异常 */
public E removeLast() {
    final Node<E> l = last;
    if (l == null) {
        throw new NoSuchElementException();
    }
    return unlinkLast(l);
}
/* 移除链表头节点，头节点为空则返回 null */
public E poll() {
    final Node<E> f = first;
    return (f == null) ? null : unlinkFirst(f);
}
/* 移除链表头节点，头节点为空则返回 null */
public E pollFirst() {
    final Node<E> f = first;
    return (f == null) ? null : unlinkFirst(f);
}
/* 移除链表尾节点，尾节点为空则返回 null */
public E pollLast() {
    final Node<E> l = last;
    return (l == null) ? null : unlinkLast(l);
}
```
> 代码清单：LinkedList 移除方法

移除元素的链表操作方法也比较简单，常规双向链表移除操作。

```java
/**
 * 移除链表头节点。
 */
private E unlinkFirst(Node<E> f) {
    // assert f == first && f != null;
    /* 取出待移除头节点数据域元素 */
    final E element = f.item;
    /* 取得待移除头节点后继节点 */
    final Node<E> next = f.next;
    /* 将待移除节点数据域、指针域置空，帮助 GC 垃圾回收 */
    f.item = null;
    f.next = null;
    /* 新头节点置为待移除头节点后继节点 */
    first = next;
    /**
     * 节点为空：尾节点指向 null
     * 节点不为空：前驱节点指针指向 null
     */
    if (next == null) {
        last = null;
    } else {
        next.prev = null;
    }
    /* 容器存储元素数量 - 1 */
    size--;
    /* 容器操作次数 + 1 */
    modCount++;
    /* 返回目标元素 */
    return element;
}
/**
 * 移除链表尾节点。
 */
private E unlinkLast(Node<E> l) {
    // assert l == last && l != null;
    /* 取出待移除尾节点数据域元素 */
    final E element = l.item;
    /* 取得待移除尾节点前驱节点 */
    final Node<E> prev = l.prev;
    /* 将待移除尾节点数据域、指针域置空，帮助 GC 垃圾回收 */
    l.item = null;
    l.prev = null;
    /* 新尾节点置为待移除尾节点前驱节点 */
    last = prev;
    /**
     * 节点为空：头节点指向 null
     * 节点不为空：后继节点指针指向 null
     */
    if (prev == null) {
        first = null;
    } else {
        prev.next = null;
    }
    /* 容器存储元素数量 - 1 */
    size--;
    /* 容器操作次数 + 1 */
    modCount++;
    /* 返回目标元素 */
    return element;
}
/**
 * 移除链表指定节点。
 */
E unlink(Node<E> x) {
    // assert x != null;
    /* 取得待移除节点数据域元素 */
    final E element = x.item;
    /* 取得待移除节点后继节点 */
    final Node<E> next = x.next;
    /* 取得待移除节点前驱节点 */
    final Node<E> prev = x.prev;
    /**
     * 前驱节点为空：头节点后移（指定节点为头节点）
     * 前驱节点不为空：后继节点指针指向待移除节点后继节点
     */
    if (prev == null) {
        first = next;
    } else {
        prev.next = next;
        x.prev = null;
    }
    /**
     * 后继节点为空：尾节点前移（指定节点为尾节点）
     * 后继节点不为空：前驱节点指针指向待移除节点前驱节点
     */
    if (next == null) {
        last = prev;
    } else {
        next.prev = prev;
        x.next = null;
    }
    /* 将待移除节点数据域、指针域置空，帮助 GC 垃圾回收 */
    x.item = null;
    /* 容器存储元素数量 - 1 */
    size--;
    /* 容器操作次数 + 1 */
    modCount++;
    /* 返回目标元素 */
    return element;
}
```
> 代码清单：LinkedList 链表移除方法

## LinkedList 获取 & 设置元素方法

LinkedList 获取 & 设置元素的基本步骤：

1. 使用`node()`方法根据「索引位置」取得待操作节点。

2. 执行节点操作。

3. 返回结果。

```java
/* 取得链表指定位置元素 */
public E get(int index) {
    /* 边界检查 */
    checkElementIndex(index);
    return node(index).item;
}
/* 取得链表头部元素，头节点为空则抛出异常 */
public E getFirst() {
    final Node<E> f = first;
    if (f == null) {
        throw new NoSuchElementException();
    }
    return f.item;
}
/* 取得链表尾部元素，尾节点为空则抛出异常 */
public E getLast() {
    final Node<E> l = last;
    if (l == null) {
        throw new NoSuchElementException();
    }
    return l.item;
}
/* 取得链表头部元素，头节点为空则返回 null */
public E peek() {
    final Node<E> f = first;
    return (f == null) ? null : f.item;
}
/* 取得链表头部元素，头节点为空则返回 null */
public E peekFirst() {
    final Node<E> f = first;
    return (f == null) ? null : f.item;
}
/* 取得链表尾部元素，尾节点为空则返回 null */
public E peekLast() {
    final Node<E> l = last;
    return (l == null) ? null : l.item;
}
/* 将链表指定位置元素设为新值 */
public E set(int index, E element) {
    /* 边界检查 */
    checkElementIndex(index);
    /* 取得操作节点 */
    Node<E> x = node(index);
    /* 替换元素 */
    E oldVal = x.item;
    x.item = element;
    /* 返回旧值 */
    return oldVal;
}
```
> 代码清单：LinkedList 获取 & 设置元素方法

返回指定位置链表节点`node(int index)`方法与边界检查方法在获取 & 设置元素，添加、移除链表节点都起着至关重要的作用。

- `node()`方法具体实现：遍历链表元素。

- 边界检查方法具体实现：检查索引位置是否大于链表存储元素数量。

遍历操作通常效率不高，LinkedList 对`node()`方法做了尽可能地优化：根据索引位置位于链表哪一侧（前半段、后半段）决定从头节点还是尾节点开始遍历。

```java
/**
 * 返回指定位置的链表节点。
 */
Node<E> node(int index) {
    // assert isElementIndex(index);
    /**
     * 指定索引位置位于链表前半段：从头节点遍历
     * 指定索引位置位于链表后半段：从尾节点遍历
     */
    if (index < (size >> 1)) {
        Node<E> x = first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    } else {
        Node<E> x = last;
        for (int i = size - 1; i > index; i--) {
            x = x.prev;
        }
        return x;
    }
}
/**
 * 越界错误提示信息
 */
private String outOfBoundsMsg(int index) {
    return "Index: " + index + ", Size: " + size;
}
/**
 * 越界检查
 */
private void checkElementIndex(int index) {
    if (!isElementIndex(index)) {
        throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
}
/**
 * 越界检查
 */
private void checkPositionIndex(int index) {
    if (!isPositionIndex(index)) {
        throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
}
/**
 * 检测指定位置元素是否存在。
 */
private boolean isElementIndex(int index) {
    return index >= 0 && index < size;
}
/**
 * 检测指定位置是否合法（提供给迭代器与新增元素方法调用）。
 */
private boolean isPositionIndex(int index) {
    return index >= 0 && index <= size;
}
```
> 代码清单：LinkedList 关键方法

## LinkedList 其他方法

LinkedList 其他一些方法实现。

```java
/**
 * 容器是否包含指定元素。
 *
 * @param o 指定元素
 * @return 布尔值，true 表示容器包含指定元素
 */
public boolean contains(Object o) {
    return indexOf(o) != -1;
}
/**
 * 返回指定元素在链表中的索引位置，
 * 从头部遍历寻找第一个匹配的元素，找不到则返回 -1 。
 *
 * @param o 搜索元素
 * @return 指定元素索引位置，不存在指定元素则返回 -1
 */
public int indexOf(Object o) {
    int index = 0;
    if (o == null) {
        for (Node<E> x = first; x != null; x = x.next) {
            if (x.item == null) {
                return index;
            }
            index++;
        }
    } else {
        for (Node<E> x = first; x != null; x = x.next) {
            if (o.equals(x.item)) {
                return index;
            }
            index++;
        }
    }
    return -1;
}
/**
 * 返回指定元素在链表中的索引位置，
 * 从尾部遍历寻找第一个匹配的元素，找不到则返回 -1 。
 *
 * @param o 搜索元素
 * @return 指定元素索引位置，不存在指定元素则返回 -1
 */
public int lastIndexOf(Object o) {
    int index = size;
    if (o == null) {
        for (Node<E> x = last; x != null; x = x.prev) {
            index--;
            if (x.item == null) {
                return index;
            }
        }
    } else {
        for (Node<E> x = last; x != null; x = x.prev) {
            index--;
            if (o.equals(x.item)) {
                return index;
            }
        }
    }
    return -1;
}
/**
 * 返回容器存储元素数量。
 *
 * @return 容器存储元素数量
 */
public int size() {
    return size;
}
/**
 * 清空链表。
 */
public void clear() {
    /**
     * 从头节点开始遍历，清空链表每个节点。
     * 置空链表节点是为了：
     * - 帮助 GC 做垃圾收集工作
     * - 在有外部可达迭代器的情况下，也可以确保释放内存
     */
    for (Node<E> x = first; x != null; ) {
        Node<E> next = x.next;
        x.item = null;
        x.next = null;
        x.prev = null;
        x = next;
    }
    /* 置空头节点、尾节点 */
    first = last = null;
    /* 容量置为 0 */
    size = 0;
    /* 容器操作次数 + 1 */
    modCount++;
}
```
> 代码清单：LinkedList 其他方法实现

## LinkedList 迭代器

LinkedList 实现了`ListIterator`迭代器，支持前后双向迭代遍历。具体实现上，利用双向链表的特性，保留前后项迭代节点。

另外，迭代器使用了 *Fail Fast* 机制，发现并发修改数据不一致的情况，快速失败。

```java
private class ListItr implements ListIterator<E> {
    /* 前项节点 */
    private Node<E> lastReturned;
    /* 后项节点 */
    private Node<E> next;
    /* 后项节点索引位 */
    private int nextIndex;
    /* 期望修改次数 */
    private int expectedModCount = modCount;
    /* 构造函数：从指定索引位置开始迭代 */
    ListItr(int index) {
        // assert isPositionIndex(index);
        /* 将指定索引位置节点赋予后项节点 */
        next = (index == size) ? null : node(index);
        /* 将指定索引位置赋予后项节点索引位 */
        nextIndex = index;
    }
    /* hasNext()：判断后项节点索引位是否超出链表容量 */
    public boolean hasNext() {
        return nextIndex < size;
    }
    /* next()：取得迭代元素 */
    public E next() {
        /* 并发检查 */
        checkForComodification();
        /* 不存在后项节点，抛出异常 */
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        /* 后项节点变为前项节点 */
        lastReturned = next;
        /* 后项节点向后迭代一轮 */
        next = next.next;
        /* 后项节点索引位 + 1 */
        nextIndex++;
        /* 返回迭代元素 */
        return lastReturned.item;
    }
    /* hasPrevious()：判断后项节点索引位是否大于 0 */
    public boolean hasPrevious() {
        return nextIndex > 0;
    }
    /* previous()：取得前项迭代元素 */
    public E previous() {
        /* 并发检查 */
        checkForComodification();
        /* 不存在前项节点，抛出异常 */
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        /* 前项节点变为后项节点 */
        lastReturned = next = (next == null) ? last : next.prev;
        /* 后项节点索引位 - 1 */
        nextIndex--;
        /* 返回迭代元素 */
        return lastReturned.item;
    }
    /* nextIndex()：取得后项迭代元素索引位置 */
    public int nextIndex() {
        return nextIndex;
    }
    /* nextIndex()：取得前项迭代元素索引位置 */
    public int previousIndex() {
        return nextIndex - 1;
    }
    /* 通过迭代器移除元素 */
    public void remove() {
        /* 并发检查 */
        checkForComodification();
        /* 状态检查 */
        if (lastReturned == null) {
            throw new IllegalStateException();
        }
        /* 取得后项节点 */
        Node<E> lastNext = lastReturned.next;
        /* 释放前项节点 */
        unlink(lastReturned);
        if (next == lastReturned) {
            next = lastNext;
        } else {
            nextIndex--;
        }
        /* 前项节点置空 */
        lastReturned = null;
        /* 期望修改次数 + 1，与实际修改次数对应 */
        expectedModCount++;
    }
    /* 通过迭代器设置元素 */
    public void set(E e) {
        /* 状态检查 */
        if (lastReturned == null) {
            throw new IllegalStateException();
        }
        /* 并发检查 */
        checkForComodification();
        /* 设置元素 */
        lastReturned.item = e;
    }
    /* 通过迭代器添加元素 */
    public void add(E e) {
        /* 并发检查 */
        checkForComodification();
        /* 前项节点置空 */
        lastReturned = null;
        /* 添加元素 */
        if (next == null) {
            linkLast(e);
        } else {
            linkBefore(e, next);
        }
        /* 后项节点索引位置 + 1 */
        nextIndex++;
        /* 期望修改次数 + 1，与实际修改次数对应 */
        expectedModCount++;
    }
    /* 并发修改检测方法 */
    final void checkForComodification() {
        /* 检查「实际修改次数」与「期望修改次数」是否一致 */
        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }
}
```
> 代码清单：LinkedList 迭代器

# LinkedList 总结

- LinkedList 实际使用双向链表实现。

- LinkedList 实现了多个集合接口，可以被用作列表、双端队列、栈等。

- LinkedList 维护双向链表节点内部类`Node`，包括数据域、前驱节点指针、后继节点指针。

- LinkedList 无需扩缩容，也不用担心容量不足的问题。

- LinkedList 克隆方法`clone()`，将全部元素拷贝到一个新的 LinkedList 对象中。

- LinkedList 实现序列化接口`java.io.Serializable`：写入时，先写入“容量”，再依次写入每一个节点保存的值；读出时，先读取“容量”，再依次读取每一个元素。

- LinkedList 使用迭代器迭代遍历效率较高，`for()`循环直接遍历效率低下。

[Collections-LinkedList-1-Hierarchy]: ../../images/Collections-LinkedList-1-Hierarchy.png

[Collections-LinkedList-2-DataStructure]: ../../images/Collections-LinkedList-2-DataStructure.png

<!-- EOF -->
