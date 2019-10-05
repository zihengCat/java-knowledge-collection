# Java `ArrayDeque` 简介

ArrayDeque 是「动态数组」实现的「双端队列」线性数据结构容器，不允许存放`null`空值，在 JDK 1.6 版本被加入到 Java 集合框架中，是一个比较新的 Java 集合框架实现类。另外，ArrayDeque 并不是线程安全的集合容器。

# ArrayDeque 继承体系

从 ArrayDeque 继承关系图中可以看出，ArrayDeque 继承自抽象父类`AbstractCollection`，实现了`Deque`接口。

![Collections-ArrayDeque-1-Hierarchy][Collections-ArrayDeque-1-Hierarchy]

> 图：ArrayDeque 继承体系图

# ArrayDeque 数据结构

ArrayDeque 底层数据结构采用「对象数组」，数组可动态扩容；另有队头指针、队尾指针，指示队头队尾在数组中的索引下标。另外，ArrayDeque 还将对象数组用作一个「循环数组」，移除元素时不需要移动队列中其他元素，只需调整队列指针位置即可，大大提高了运行效率。

![Collections-ArrayDeque-2-DataStructure][Collections-ArrayDeque-2-DataStructure]

> 图：ArrayDeque 数据结构

# ArrayDeque 基本用法

以下代码展示了 ArrayDeque 基本使用方式。

```java
import java.util.Deque;
import java.util.ArrayDeque;
public class ArrayDequeTest {
    public static void main(String[] args) {
        /* 创建 ArrayDeque */
        Deque<Integer> arrayDeque = new ArrayDeque<Integer>();
        /* 向 ArrayDeque 队头加入元素 */
        arrayDeque.offerFirst(1);
        /* 向 ArrayDeque 队尾加入元素 */
        arrayDeque.offerLast(2);
        /* 检查 ArrayDeque 元素数量 */
        System.out.println("ArrayDeque.size(): " + arrayDeque.size());
        /* 取出 ArrayDeque 队头元素 */
        System.out.println(arrayDeque.pollFirst());
        /* 取出 ArrayDeque 队尾元素 */
        System.out.println(arrayDeque.pollLast());
        /* 再次检查 ArrayDeque 元素数量 */
        System.out.println("ArrayDeque.size(): " + arrayDeque.size());
    }
}
/* EOF */
```
> 代码清单：ArrayDeque 基本用法

# ArrayDeque 源码剖析

从 JDK 源码角度深入分析 ArrayDeque 实现。

## ArrayDeque 数据字段

观察 ArrayDeque 中的数据字段，可以得知：

- 底层数据结构为「对象数组」。

- 队列容量必须为`2`的幂次。

- 最小队列容量为`8`。

- 队头指针指向队列头元素所在数组的索引下标。

- 队尾指针指向下一个新加入元素（并非队尾元素）所在数组的索引下标。

```java
public class ArrayDeque<E> extends AbstractCollection<E>
                           implements Deque<E>, Cloneable, Serializable
{
    /* 序列化号 */
    private static final long serialVersionUID = 2340985798034038923L;

    /* 队列最小容量，必须为 2 的幂次 */
    private static final int MIN_INITIAL_CAPACITY = 8;

    /* 对象数组 */
    transient Object[] elements; // 不标注为 private 是为了简化内部类访问

    /* 队头指针（队头元素在数组中的索引下标） */
    transient int head;

    /* 队尾指针（下一个新加入元素在数组中的索引下标） */
    transient int tail;
}
```
> 代码清单：ArrayDeque 数据字段

## ArrayDeque 构造函数

与 ArrayList 相似，ArrayDeque 支持三种构造方式：

- 无参数默认构造：以默认容量`16`构造空对象数组。

- 指定容量构造：以指定容量构造空对象数组。

- 以`Collection`序列对象构造：构造序列对象迭代器返回元素顺序的对象数组。

```java
/**
 * 无参数默认构造，以默认容量 16 构造空对象数组。
 *
 * @param void
 */
public ArrayDeque() {
    elements = new Object[16];
}
/**
 * 指定容量构造，以指定容量构造空对象数组。
 *
 * @param numElements 指定容量
 */
public ArrayDeque(int numElements) {
    allocateElements(numElements);
}
/**
 * 以 Collection 序列对象构造序列对象迭代器返回元素顺序的对象数组。
 *
 * @param c 序列对象
 * @throws NullPointerException 如果序列对象为空
 */
public ArrayDeque(Collection<? extends E> c) {
    allocateElements(c.size());
    addAll(c);
}
```
> 代码清单：ArrayDeque 构造函数

## ArrayDeque 容量分配

ArrayDeque 在容量分配时保证实际分配容量是`2`的幂次，实际容量计算方法为`calculateSize()`，`>>>`是无符号右移操作，`|`是位或操作，经过五次右移和位或操作后得到最接近指定元素数量的`2 ^ n - 1`数，将该数加`1`，得`2 ^ n`数。

```java
/**
 * 分配能存储指定数量元素的空数组。
 *
 * @param numElements 元素数量
 */
private void allocateElements(int numElements) {
    elements = new Object[calculateSize(numElements)];
}
/**
 * 容量计算方法。
 *
 * @param numElements 元素数量
 * @return int 实际容量
 */
private static int calculateSize(int numElements) {
    /* 初始化容量为 8 */
    int initialCapacity = MIN_INITIAL_CAPACITY;
    // Find the best power of two to hold elements.
    // Tests "<=" because arrays aren't kept full.
    if (numElements >= initialCapacity) {
        initialCapacity = numElements;
        initialCapacity |= (initialCapacity >>>  1);
        initialCapacity |= (initialCapacity >>>  2);
        initialCapacity |= (initialCapacity >>>  4);
        initialCapacity |= (initialCapacity >>>  8);
        initialCapacity |= (initialCapacity >>> 16);
        initialCapacity++;

        if (initialCapacity < 0)   // Too many elements, must back off
            initialCapacity >>>= 1;// Good luck allocating 2 ^ 30 elements
    }
    return initialCapacity;
}
```
> 代码清单：ArrayDeque 容量分配方法

通过以下例子，可以更加深刻理解`calculateSize()`方法计算逻辑。

```java
/**
 * 0 0 0 0 1 ? ? ? ? ?     // n
 * 0 0 0 0 1 1 ? ? ? ?     // n |= n >>> 1;
 * 0 0 0 0 1 1 1 1 ? ?     // n |= n >>> 2;
 * 0 0 0 0 1 1 1 1 1 1     // n |= n >>> 4;
 * ...
 * 0 0 0 1 0 0 0 0 0 0     // n++
 *
 * 以 calculateSize(17) 为例：
 *
 * 17 = 0x11
 * >>>  1 : 0001 0001 | 0000 1000 = 0001 1001 (25)
 * >>>  2 : 0001 1001 | 0000 0110 = 0001 1111 (31)
 * >>>  4 : 0001 1111 | 0000 1000 = 0001 1111 (31)
 * >>>  8 : 0001 1111 | 0000 0000 = 0001 1111 (31)
 * >>> 16 : 0001 1111 | 0000 0000 = 0001 1111 (31)
 * x += 1 : 0001 1111 + 0000 0001 = 0010 0000 (32)
 */
```
> 注：`calculateSize()`方法计算逻辑

## ArrayDeque 添加元素

ArrayDeque 实现了`Deque`接口定义的所有添加元素方法：`add()`、`addFirst()`、`addLast()`、`offer()`、`offerFirst()`、`offerLast()`、`push()`，具体实现放在添加元素至队头`addFirst()`方法，添加元素至队尾`addLast()`方法这两枚方法之中，其他添加元素方法都是调用这两枚方法实现的。

```java
/**
 * 将指定元素添加到双端队列头部。
 *
 * @param e 需要添加的元素
 * @return void
 * @throws NullPointerException 如果元素为空
 */
public void addFirst(E e) {
    /* 指定添加元素为空，抛出空指针异常 */
    if (e == null) {
        throw new NullPointerException();
    }
    /* 队头指针前移1位，在队头位置存放元素 */
    elements[head = (head - 1) & (elements.length - 1)] = e;
    /* 如果移动后队尾指针与队头指针重合，
       说明数组已满，执行扩容操作 */
    if (head == tail) {
        doubleCapacity();
    }
}
/**
 * 将指定元素添加到双端队列尾部。
 *
 * @param e 需要添加的元素
 * @return void
 * @throws NullPointerException 如果元素为空
 */
public void addLast(E e) {
    /* 指定添加元素为空，抛出空指针异常 */
    if (e == null) {
        throw new NullPointerException();
    }
    /* 队尾指针指向新添加元素可存放的数组索引位置，
       在该位置直接存放元素 */
    elements[tail] = e;
    /* 队尾指针后移1位，
       如果移动后队尾指针与队头指针重合，
       说明数组已满，执行扩容操作 */
    if ((tail = (tail + 1) & (elements.length - 1)) == head) {
        doubleCapacity();
    }
}
```
> 代码清单：ArrayDeque 添加元素方法源码

ArrayDeque 添加队头元素`addFirst()`方法中：**队尾指针永远指向下一个新添加元素的索引下标，所以数组中至少存在一个空位可以存放新添加元素**；语句`head = (head - 1) & (elements.length - 1)`将数组用作循环数组，含义：**队头指针前移`1`位，如果此时索引下标未越界，即在当前位置存放元素；如果此时索引下标越界（值为：`-1`），即循环到数组尾部添加元素**。

![Collections-ArrayDeque-3-AddFirst][Collections-ArrayDeque-3-AddFirst]

> 图：ArrayDeque 添加元素`addFirst()`方法

ArrayDeque 添加队尾元素`addLast()`方法与`addFirst()`方法相似，**由于队尾指针指向下一个新添加元素的索引下标，所以直接存放元素**；语句`tail = (tail + 1) & (elements.length - 1)`将数组用作循环数组，含义：**队尾指针后移`1`位，如果此时索引下标未越界，即指向该位置；如果此时索引下标越界（值为：`elements.length`），将队尾指针指向数组头部`0`位置**。

![Collections-ArrayDeque-4-AddLast][Collections-ArrayDeque-4-AddLast]

> 图：ArrayDeque 添加元素`addLast()`方法

通过以下例子，可以更加深刻理解 ArrayDeque 添加元素位运算的计算逻辑。

```java
/**
 * head = (head - 1) & (elements.length - 1)
 * tail = (tail + 1) & (elements.length - 1)
 *
 * 1. 以索引下标 0，数组长度 16 为例：
 * ----------------------------------
 * (0 - 1) & (16 - 1)
 * = -1 & 15
 * = 1111 1111 & 0000 1111 = 0000 1111
 * = 15
 *
 * 2. 以索引下标 3，数组长度 16 为例：
 * ----------------------------------
 * (3 - 1) & (16 - 1)
 * = 2 & 15
 * = 0000 0010 & 0000 1111 = 0000 0010
 * = 2
 *
 * 3. 以索引下标 15，数组长度 16 为例：
 * ----------------------------------
 * (15 + 1) & (16 - 1)
 * = 16 & 15
 * = 0001 0000 & 0000 1111 = 0000 0000
 * = 0
 *
 * 4. 以索引下标 9，数组长度 16 为例：
 * ----------------------------------
 * (9 + 1) & (16 - 1)
 * = 10 & 15
 * = 0000 1010 & 0000 1111 = 0000 1010
 * = 10
 */
```
> 注：ArrayDeque 添加元素方法「位运算」详解

## ArrayDeque 移除元素

ArrayDeque 实现了`Deque`接口定义的所有移除元素方法：`remove()`、`removeFirst()`、`removeLast()`、`poll()`、`pollFirst()`、`pollLast()`、`pop()`。具体实现放在了移除队头元素`pollFirst()`方法，移除队尾元素`pollLast()`方法这两枚方法之中，其他移除元素方法都是调用这两枚方法实现的。

```java
/**
 * 移除并返回队头元素。
 *
 * @param void
 * @return E 队头元素，如果为空则说明队列为空
 */
public E pollFirst() {
    int h = head;
    @SuppressWarnings("unchecked")
    E result = (E) elements[h];
    // Element is null if deque empty
    if (result == null) {
        return null;
    }
    elements[h] = null;     // Must null out slot
    head = (h + 1) & (elements.length - 1);
    return result;
}
/**
 * 移除并返回队尾元素。
 *
 * @param void
 * @return E 队尾元素，如果为空则说明队列为空
 */
public E pollLast() {
    int t = (tail - 1) & (elements.length - 1);
    @SuppressWarnings("unchecked")
    E result = (E) elements[t];
    if (result == null) {
        return null;
    }
    elements[t] = null;
    tail = t;
    return result;
}
```
> 代码清单：ArrayDeque 移除元素方法源码

了解 ArrayDeque 的「位运算」机制后，移除队头、队尾元素方法就很好理解了：`pollFirst()`方法返回队头指针所指元素，并将队头指针后移`1`位；`pollLast()`方法返回队尾指针前一位元素，并将队尾指针前移`1`位。元素移除后，其数组原位置会被置空，这也是 ArrayDeque 不允许存放空值的原因。

## ArrayDeque 查看元素

ArrayDeque 查看元素相关方法与移除元素方法相似，只是不需要置空元素，也无需移动头尾指针。

```java
public E element() {
    return getFirst();
}
public E getFirst() {
    @SuppressWarnings("unchecked")
    E result = (E) elements[head];
    if (result == null) {
        throw new NoSuchElementException();
    }
    return result;
}
public E getLast() {
    @SuppressWarnings("unchecked")
    E result = (E) elements[(tail - 1) & (elements.length - 1)];
    if (result == null) {
        throw new NoSuchElementException();
    }
    return result;
}
public E peek() {
    return peekFirst();
}
@SuppressWarnings("unchecked")
public E peekFirst() {
    // elements[head] is null if deque empty
    return (E) elements[head];
}
@SuppressWarnings("unchecked")
public E peekLast() {
    return (E) elements[(tail - 1) & (elements.length - 1)];
}
```
> 代码清单：ArrayDeque 查看元素方法源码

## ArrayDeque 扩容机制

ArrayDeque 每次添加新元素后，都会检查队头队尾指针是否重合，如果队尾指针重合，说明当前数组已满，执行扩容操作。ArrayDeque 扩容操作由`doubleCapacity()`方法完成，每次扩容都为原数组两倍，具体执行逻辑为：

1. 申请一个新数组（原数组的两倍）；

2. 将队头指针右边的元素拷贝至新数组`[0, elements.length - head - 1]`位置；

3. 将队头指针左边的元素拷贝至新数组`[elements.length - head, elements.length - 1]`位置；

4. 重置队头队尾指针。

![Collections-ArrayDeque-5-DoubleCapacity][Collections-ArrayDeque-5-DoubleCapacity]

> 图：ArrayDeque 扩容示意图

```java
/**
 * 将队列扩容两倍，仅在队头、队尾指针重合时执行该操作。
 *
 * @param void
 * @return void
 */
private void doubleCapacity() {
    /* 确认队头队尾指针重合 */
    assert head == tail;
    int p = head;
    int n = elements.length;
    /* 队头指针右边的元素 */
    int r = n - p; // number of elements to the right of p
    /* 扩容 2 倍 */
    int newCapacity = n << 1;
    if (newCapacity < 0) {
        throw new IllegalStateException("Sorry, deque too big");
    }
    /* 申请新数组 */
    Object[] a = new Object[newCapacity];
    /* 将队头指针右边的元素拷贝至新数组
       [head, elements.length - 1] -> [0, elements.length - 1 - head] */
    System.arraycopy(elements, p, a, 0, r);
    /* 将队头指针左边的元素拷贝至新数组
       [0, head] -> [elements.length - head, elements.length - 1] */
    System.arraycopy(elements, 0, a, r, p);
    /* 设置新数组 */
    elements = a;
    /* 队头指针：起始位置 */
    head = 0;
    /* 队头指针：原数组长度位置 */
    tail = n;
}
```
> 代码清单：ArrayDeque 扩容方法源码

## ArrayDeque 其他方法

ArrayDeque 其他一些常用方法，这些方法都比较简单，不多赘述。

```java
/**
 * 判断队列是否为空。
 *
 * @return {@code true} 如果队列不包含任何元素
 */
public boolean isEmpty() {
    return head == tail;
}
/**
 * 返回队列中包含元素数量。
 *
 * @return {@code size} 队列包含元素数量
 */
public int size() {
    return (tail - head) & (elements.length - 1);
}
/**
 * 清空队列。
 */
public void clear() {
    int h = head;
    int t = tail;
    if (h != t) { // clear all cells
        head = tail = 0;
        int i = h;
        int mask = elements.length - 1;
        do {
            elements[i] = null;
            i = (i + 1) & mask;
        } while (i != t);
    }
}
/**
 * 队列转换数组。
 *
 * @return {@code Object[]}
 */
public Object[] toArray() {
    return copyElements(new Object[size()]);
}
/**
 * 从头到尾依序拷贝队列元素至指定数组中，传入的数组容量必须足够大。
 *
 * @return {@code a} 传入数组
 */
private <T> T[] copyElements(T[] a) {
    if (head < tail) {
        System.arraycopy(elements, head, a, 0, size());
    } else if (head > tail) {
        int headPortionLen = elements.length - head;
        System.arraycopy(elements, head, a, 0, headPortionLen);
        System.arraycopy(elements, 0, a, headPortionLen, tail);
    }
    return a;
}
```
> 代码清单：ArrayDeque 其他方法源码

# ArrayDeque 总结

- ArrayDeque 是使用「动态数组」实现的「双端队列」线性数据结构容器。

- ArrayDeque 不允许存放`null`空值。

- ArrayDeque 并不是线程安全的集合容器。

- ArrayDeque 将底层对象数组用作「循环数组」，移除元素时不需要移动队列中其他元素，只需调整队列指针位置，运行效率高。

- ArrayDeque 队列容量总是`2`的幂次，每次扩容都为原容量的`2`倍。

# 参考资料

- https://docs.oracle.com/javase/8/docs/technotes/guides/collections/index.html

[Collections-ArrayDeque-1-Hierarchy]: ../../images/Collections-ArrayDeque-1-Hierarchy.png

[Collections-ArrayDeque-2-DataStructure]: ../../images/Collections-ArrayDeque-2-DataStructure.png

[Collections-ArrayDeque-3-AddFirst]: ../../images/Collections-ArrayDeque-3-AddFirst.png

[Collections-ArrayDeque-4-AddLast]: ../../images/Collections-ArrayDeque-4-AddLast.png

[Collections-ArrayDeque-5-DoubleCapacity]: ../../images/Collections-ArrayDeque-5-DoubleCapacity.png

<!-- EOF -->
