# 数组实现队列（Queue）

队列（Queue）是一种先进先出（FIFO）的线性数据结构，具体实现上有：顺序队列、链式队列。

本文讲解在 Java 中使用数组实现顺序队列。

# 实现思路

数组实现队列结构，底层数据结构选用对象数组，还需要队头指针`head`、队尾指针`tail`两枚指针，指示队头元素与队尾元素在数组中的索引位置，再额外引入一枚`size`变量，指示队列中已存放元素数量。另外，将队列实现为范型类，这样队列就可以存放任何类型的数据了。

- 入队：将新元素放入队尾位置，队尾指针后移。

- 出队：返回队头元素，剩余队列元素整体前移一位，队尾指针也前移一位。

# 顺序队列测试代码（TDD）

秉持着测试驱动开发（TDD）原则，先编写顺序队列测试代码。

```java
/* 导入队列接口 */
import Queue;
/* 导入顺序队列实现类 */
import ArrayQueue;
/* 测试类 */
public class QueueTest {
    public static void main(String[] args) {
        testQueue(new ArrayQueue<Integer>());
    }
    public static void testQueue(Queue<Integer> queue) {
        int capacity = 128;
        queue.clear();
        System.out.println("Before enqueue elements, Queue.size(): " +
            queue.size()
        );
        for (int i = 0; i < capacity; ++i) {
            queue.enqueue(i);
        }
        System.out.println("After enqueue elements, Queue.size(): " +
            queue.size()
        );
        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
        System.out.println("After dequeue elements, Queue.size(): " +
            queue.size()
        );
    }
}
/* EOF */
```
> 代码清单：顺序队列测试代码 - `Java`代码

# 顺序队列数据字段

理解顺序队列整体实现思路后，着手编写 Java 代码：引入队列操作接口与队列异常，使用范型实现类来实现队列接口；设置底层对象数组、队头指针、队尾指针、队列容量变量。

```java
import Queue;
import QueueIsFullException;
import QueueIsEmptyException;
public class ArrayQueue<E> implements Queue<E> {
    /* 队列默认容量大小 */
    private static final int DEFAULT_CAPACITY = 1 << 4; /* 16 */
    /* 存放实际元素的对象数组 */
    private E[] elementData;
    /* 队头指针（指示队头元素） */
    private int head;
    /* 队尾指针（指示队尾元素的下一个空位） */
    private int tail;
    /* 队列已存放元素数量 */
    private int size;
}
/* EOF */
```
> 代码清单：顺序队列数据字段 - `Java`代码

# 顺序队列构造函数

为顺序队列提供构造函数，初始化过程为对象数组分配指定容量内存，队列容量置为零，队头指针指向数组起始位置，队尾指针指向下一个空闲位置的数组索引下标。

```java
/**
 * 默认无参构造函数。
 * @param void
 * @return void
 */
public ArrayQueue() {
    this(DEFAULT_CAPACITY);
}
/**
 * 指定容量构造。
 * @param capacity
 * @return void
 */
@SuppressWarnings("unchecked")
public ArrayQueue(int capacity) {
    if (capacity <= 0) {
        throw new IllegalArgumentException(
            "[ERROR]: capacity should be greater than zero"
        );
    }
    this.elementData = (E[])new Object[capacity];
    this.head = 0;
    this.tail = 0;
    this.size = 0;
}
```
> 代码清单：顺序队列构造函数 - `Java`代码

# 顺序队列入队操作

入队操作`enqueue()`将指定元素添加到队列中，一般是添加到队尾位置，其具体实现为：先检查队列容量是否足够，容量不足则抛出异常；在队尾指针位置放入新元素，队尾指针后移一位；队列存放元素数量加`1`。

```java
/**
 * 将指定元素入队。
 * @param e
 * @return void
 */
public void enqueue(E e) {
    ensureCapacity();
    elementData[tail++] = e;
    size++;
}
/**
 * 检查队列容量是否足够，容量不足则抛出异常。
 * @param void
 * @return void
 */
private void ensureCapacity() {
    if (size + 1 > elementData.length) {
        throw new QueueIsFullException("[ERROR]: Queue is full");
    }
}
```
> 代码清单：顺序队列入队操作 - `Java`代码

# 顺序队列出队操作

出队操作`dequeue()`将队头元素移除出队列，其具体实现为：先检查队列是否为空，队列为空则不允许出队操作，抛出异常；暂存队头元素，队列剩余元素整体前移`1`位，队尾指针也前移`1`位，队列存放元素数量减`1`，最后返回目标元素。

```java
/**
 * 将队头元素出队。
 * @param void
 * @return E
 */
public E dequeue() {
    /* 队列为空，则不允许出队操作，抛出异常 */
    if (isEmpty()) {
        throw new QueueIsEmptyException("[ERROR]: Queue is empty");
    }
    /* 保存队头元素 */
    E element = elementData[head];
    /* 队列剩余元素整体前移 1 位 */
    arrayCopy(elementData, head + 1, elementData, head, size - 1);
    /* 队尾指针也前移 1 位 */
    tail--;
    /* 队列元素数量减 1 个 */
    size--;
    /* 返回目标元素 */
    return element;
}
/**
 * 数组元素拷贝函数。
 *
 * @param src     源数组
 * @param srcPos  源数组起始位置
 * @param dest    目标数组
 * @param destPos 目标数组起始位置
 * @param length  拷贝元素长度
 *
 * @return void
 */
private void arrayCopy(
    E[] src, int srcPos,
    E[] dest, int destPos,
    int length) {
    // System.arraycopy(src, srcPos, dest, destPos, length);
    for (int i = 0; i < length; ++i) {
        dest[destPos + i] = src[srcPos + i];
    }
}
```
> 代码清单：顺序队列出队操作 - `Java`代码

# 顺序队列其他操作

接下来，实现顺序队列接口其他一些操作，这些操作的具体实现都比较简单。

```java
/**
 * 查看队头元素。
 * @param void
 * @return E
 */
public E peek() {
    if (isEmpty()) {
        throw new QueueIsEmptyException("[ERROR]: Queue is empty");
    }
    return elementData[head];
}
/**
 * 查看队头已存放元素数量。
 * @param void
 * @return size
 */
public int size() {
    return size;
}
/**
 * 检查队列是否为空。
 * @param void
 * @return boolean
 */
public boolean isEmpty() {
    return head == tail;
}
/**
 * 清空队列。
 * @param void
 * @return void
 */
public void clear() {
    for (int i = head; i < tail; ++i) {
        elementData[i] = null;
    }
    this.head = 0;
    this.tail = 0;
    this.size = 0;
}
```
> 代码清单：顺序队列其他操作 - `Java`代码

# 顺序队列动态扩容

在上述顺序栈实现中，队列在初始化时指定容量，队列无法动态扩容，在队列满后再入队数据，会抛出`QueueIsFullException`异常。但我们可以改写代码，实现顺序队列动态扩容。具体实现逻辑如下：

1. 发现队列容量已满，执行扩容操作；

2. 创建一个新数组（新数组容量为原容量的`2`倍）；

3. 将旧数组中的元素按顺序依次拷贝至新数组中；

4. 将队列数组替换为新数组。

```java
/**
 * 检查队列容量是否足够，容量不足则执行扩容操作。
 * @param void
 * @return void
 */
private void ensureCapacity() {
    if (size + 1 > elementData.length) {
        // throw new QueueIsFullException("[ERROR]: Queue is full");
        grow();
    }
}
/**
 * 队列扩容（2倍）。
 * @param void
 * @return void
 */
@SuppressWarnings("unchecked")
private void grow() {
    int oldCapacity = elementData.length;
    int newCapacity = oldCapacity << 1;
    E[] oldElementData = this.elementData;
    E[] newElementData = (E[])new Object[newCapacity];
    for (int i = head; i < tail; ++i) {
        newElementData[i] = oldElementData[i];
        oldElementData[i] = null;
    }
    this.elementData = newElementData;
}
```
> 代码清单：顺序队列动态扩容 - `Java`代码

# 完整代码（Java）

给出数组实现队列完整`Java`代码。

```java
import Queue;
import QueueIsFullException;
import QueueIsEmptyException;

public class ArrayQueue<E> implements Queue<E> {

    private static final int DEFAULT_CAPACITY = 1 << 4;
    private E[] elementData;
    private int head;
    private int tail;
    private int size;

    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(
                "[ERROR]: capacity should be greater than zero"
            );
        }
        this.elementData = (E[])new Object[capacity];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    public void enqueue(E e) {
        ensureCapacity();
        elementData[tail++] = e;
        size++;
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new QueueIsEmptyException("[ERROR]: Queue is empty");
        }
        E element = elementData[head];
        arrayCopy(elementData, head + 1, elementData, head, size - 1);
        tail--;
        size--;
        return element;
    }

    public E peek() {
        if (isEmpty()) {
            throw new QueueIsEmptyException("[ERROR]: Queue is empty");
        }
        return elementData[head];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public void clear() {
        for (int i = head; i < tail; ++i) {
            elementData[i] = null;
        }
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    private void arrayCopy(
        E[] src, int srcPos,
        E[] dest, int destPos,
        int length) {
        // System.arraycopy(src, srcPos, dest, destPos, length);
        for (int i = 0; i < length; ++i) {
            dest[destPos + i] = src[srcPos + i];
        }
    }

    private void ensureCapacity() {
        if (size + 1 > elementData.length) {
            // throw new QueueIsFullException("[ERROR]: Queue is full");
            grow();
        }
    }

    @SuppressWarnings("unchecked")
    private void grow() {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity << 1;
        E[] oldElementData = this.elementData;
        E[] newElementData = (E[])new Object[newCapacity];
        for (int i = head; i < tail; ++i) {
            newElementData[i] = oldElementData[i];
            oldElementData[i] = null;
        }
        this.elementData = newElementData;
    }
}
/* EOF */
```
> 代码清单：数组实现队列 - `Java`代码

<!-- EOF -->
