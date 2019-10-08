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
> 代码清单：顺序队列测试代码

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

<!-- EOF -->
