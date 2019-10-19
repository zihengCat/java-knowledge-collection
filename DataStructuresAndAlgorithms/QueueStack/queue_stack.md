# 队列实现栈（Java）

栈（Stack）是一种后进先出（LIFO）的线性数据结构，队列（Queue）是一种先进先出（FIFO）的线性数据结构。

那么，能否使用队列实现出栈结构呢？答案是肯定的。

# 算法思路

队列的性质是先进先出，栈的性质是后进先出，使用队列实现栈结构，需要使用两个队列：将元素在主副队列之间来回搬移，实现栈结构「后进先出」的特性。

- **压栈操作**：将元素入队主队列。

- **弹栈操作**：先将主队列中的元素存入副队列，仅保留最后一枚队尾元素（即：栈顶元素），将该元素移出主队列并返回，最后把副队列中的元素重新放回主队列。

# 代码实现

为了简单起见，直接使用 Java 标准库中的`java.util.Queue`作为队列，并复用先前写好的测试代码、`Stack`接口、栈异常类。

```java
import Stack;
import StackIsFullException;
import StackIsEmptyException;
import QueueStack;
public class QueueStackTest {
    public static void main(String[] args) {
        int capacity = 128;
        Stack<Integer> queueStack = new QueueStack<Integer>();
        System.out.println("Before push elements, QueueStack.size(): " +
            queueStack.size()
        );
        for (int i = 0; i < capacity; ++i) {
            queueStack.push(i);
        }
        System.out.println("After push elements, QueueStack.size(): " +
            queueStack.size()
        );
        System.out.println("QueueStack.top(): " + queueStack.top());
        while (!queueStack.isEmpty()) {
            System.out.println(queueStack.pop());
        }
        System.out.println("After pop elements, QueueStack.size(): " +
            queueStack.size()
        );
    }
}
/* EOF */
```
> 代码清单：队列栈测试代码（Java）

```java
/* 引入标准库队列 */
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayDeque;
/* 引入栈接口、栈异常 */
import Stack;
import StackIsEmptyException;
import StackIsFullException;
/* 队列栈实现类 */
public class QueueStack<E> implements Stack<E> {
    /* 主队列 */
    private Queue<E> primaryQueue;
    /* 副队列 */
    private Queue<E> secondaryQueue;
    /* 构造函数 */
    public QueueStack() {
        /* 初始化主、副队列 */
        this.primaryQueue = new ArrayDeque<E>();
        this.secondaryQueue = new LinkedList<E>();
    }
    public void push(E e) {
        /* 压栈操作：正常入主队列 */
        primaryQueue.offer(e);
    }
    public E pop() {
        if (isEmpty()) {
            throw new StackIsEmptyException("[ERROR]: Stack is empty");
        }
        /* 将主队列除队尾元素外所有元素移动到副队列 */
        while (primaryQueue.size() > 1) {
            secondaryQueue.offer(
                primaryQueue.poll()
            );
        }
        /* 取得队尾元素 */
        E element = primaryQueue.poll();
        /* 将副队列中所有元素移回主队列 */
        while (!secondaryQueue.isEmpty()) {
            primaryQueue.offer(
                secondaryQueue.poll()
            );
        }
        /* 返回目标元素 */
        return element;
    }
    public E top() {
        if (isEmpty()) {
            throw new StackIsEmptyException("[ERROR]: Stack is empty");
        }
        while (primaryQueue.size() > 1) {
            secondaryQueue.offer(
                primaryQueue.poll()
            );
        }
        /* 与弹栈操作唯一不同：暂存队尾元素，并不移除 */
        E element = primaryQueue.peek();
        secondaryQueue.offer(primaryQueue.poll());
        while (!secondaryQueue.isEmpty()) {
            primaryQueue.offer(secondaryQueue.poll());
        }
        return element;
    }
    public int size() {
        return primaryQueue.size() + secondaryQueue.size();
    }
    public boolean isEmpty() {
        return primaryQueue.isEmpty() && secondaryQueue.isEmpty();
    }
    public void clear() {
        primaryQueue.clear();
        secondaryQueue.clear();
    }
}
/* EOF */
```
> 代码清单：队列栈实现代码（Java）

<!-- EOF -->
