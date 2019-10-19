# 栈实现队列（Java）

队列（Queue）是一种先进先出（FIFO）的线性数据结构，栈（Stack）是一种后进先出（LIFO）的线性数据结构。

那么，能否使用栈实现出队列数据结构呢？答案是肯定的。

# 算法思路

队列的特征是先进先出，栈的特征是后进先出，使用栈实现队列结构，需要使用两个栈：将元素在主副栈中来回搬运，实现队列结构先进先出的性质。

- 入队：将元素压入主栈。

- 出队：先将主栈中的元素存入副栈中，只留下最后一枚栈底元素（即：队头元素），将该元素弹出主栈并返回，最后把副栈中的元素重新放回主栈。

# 代码实现

为了简单起见，直接使用 Java 标准库中的`java.util.Deque`作为栈，并复用先前写好的测试代码、`Queue`接口、队列异常类。

```java
import Queue;
import StackQueue;
public class QueueTest {
    public static void main(String[] args) {
        testQueue(new StackQueue<Integer>());
    }
    public static void testQueue(Queue<Integer> queue) {
        /* test Queue.clear() */
        queue.enqueue(0);
        System.out.println("Before clear elements, Queue.size(): " +
            queue.size()
        );
        queue.clear();
        System.out.println("After clear elements, Queue.size(): " +
            queue.size()
        );
        /* test Queue.enqueue() */
        int capacity = 128;
        System.out.println("Before enqueue elements, Queue.size(): " +
            queue.size()
        );
        for (int i = 0; i < capacity; ++i) {
            queue.enqueue(i);
        }
        System.out.println("After enqueue elements, Queue.size(): " +
            queue.size()
        );
        /* test Queue.dequeue() */
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
> 代码清单：栈队列测试代码（Java）

```java
import Queue;
import QueueIsFullException;
import QueueIsEmptyException;

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.LinkedList

public class StackQueue<E> implements Queue<E> {

    private Deque<E> primaryStack;
    private Deque<E> secondaryStack;

    public StackQueue() {
        this.primaryStack = new ArrayDeque<E>();
        this.secondaryStack = new LinkedList<E>();
    }

    public void enqueue(E e) {
        primaryStack.push(e);
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new QueueIsEmptyException("[ERROR]: Queue is empty");
        }
        while (primaryStack.size() > 1) {
            secondaryStack.push(
                primaryStack.pop()
            );
        }
        E element = primaryStack.pop();
        while (!secondaryStack.isEmpty()) {
            primaryStack.push(
                secondaryStack.pop()
            );
        }
        return element;
    }

    public E peek() {
        if (isEmpty()) {
            throw new QueueIsEmptyException("[ERROR]: Queue is empty");
        }
        while (primaryStack.size() > 1) {
            secondaryStack.push(
                primaryStack.pop()
            );
        }
        E element = primaryStack.element();
        secondaryStack.push(primaryStack.pop());
        while (!secondaryStack.isEmpty()) {
            primaryStack.push(
                secondaryStack.pop()
            );
        }
        return element;
    }

    public int size() {
        return primaryStack.size() + secondaryStack.size();
    }

    public boolean isEmpty() {
        return primaryStack.isEmpty() && secondaryStack.isEmpty();
    }

    public void clear() {
        primaryStack.clear();
        secondaryStack.clear();
    }
}
/* EOF */
```
> 代码清单：栈队列实现代码（Java）

<!-- EOF -->
