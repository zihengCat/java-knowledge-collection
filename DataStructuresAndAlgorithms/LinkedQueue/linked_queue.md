# 链表实现队列（Queue）

队列（Queue）是一种先进先出（FIFO）的线性数据结构，具体实现上有：顺序队列、链式队列。

本文讲解在 Java 中使用「双向链表」实现链式队列。

# 实现思路

链表实现队列结构，底层数据结构选用「双向链表」，准备队头指针`head`与队尾指针`tail`，再引入一个`size`变量纪录队列中已存放元素数量。

- 入队：将新元素包装为双向链表节点，使用尾插法插入到双向链表中，队尾指针指向新节点。

- 出队：暂存队头指针所指节点，队头指针后移一位，从链表中移除旧队头节点，返回旧队头节点元素。

# 链式队列测试代码（TDD）

秉持着测试驱动开发（TDD）原则，先编写链式队列测试代码。

```java
import Queue;
import LinkedQueue;
public class QueueTest {
    public static void main(String[] args) {
        testQueue(new LinkedQueue<Integer>());
    }
    public static void testQueue(Queue<Integer> queue) {
        int capacity = 128;
        queue.enqueue(0);
        System.out.println("Before clear elements, Queue.size(): " +
            queue.size()
        );
        queue.clear();
        System.out.println("After clear elements, Queue.size(): " +
            queue.size()
        );
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
> 代码清单：链式队列测试代码 - `Java`代码

# 链式队列数据字段

理解链式队列整体实现思路后，着手编写 Java 代码：引入队列操作接口与队列异常，使用范型类来实现队列接口；维护双向链表节点内部类、队头指针、队尾指针、队列容量变量。

```java
import Queue;
import QueueIsFullException;
import QueueIsEmptyException
public class LinkedQueue<E> implements Queue<E> {
    /* 队头指针 */
    private Node<E> head;
    /* 队尾指针 */
    private Node<E> tail;
    /* 队列已存放元素数量 */
    private int size;
    /* 双向链表节点类 */
    private class Node<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;
        public Node() {
            this(null, null, null);
        }
        public Node(E element) {
            this(element, null, null);
        }
        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
        public E getElement() {
            return element;
        }
        public void setElement(E element) {
            this.element = element;
        }
        public Node<E> getPrev() {
            return prev;
        }
        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }
        public Node<E> getNext() {
            return next;
        }
        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
}
```
> 代码清单：链式队列数据字段 - `Java`代码

# 链式队列构造函数

链式队列一般无需限定队列容量，构造函数初始化过程：队头指针、队尾指针都指向`null`空值，将队列容量置为`0`。

```java
/**
 * 默认无参构造函数。
 * @param void
 */
public LinkedQueue() {
    this.head = null;
    this.tail = this.head;
    this.size = 0;
}
```
> 代码清单：链式队列构造函数 - `Java`代码

# 完整代码（Java）

给出链表实现队列完整`Java`代码。

```java
import Queue;
import QueueIsFullException;
import QueueIsEmptyException;

public class LinkedQueue<E> implements Queue<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LinkedQueue() {
        this.head = null;
        this.tail = this.head;
        this.size = 0;
    }

    public void enqueue(E e) {
        Node<E> oldTail = tail;
        Node<E> newNode = new Node<E>(e);
        newNode.setNext(null);
        newNode.setPrev(oldTail);
        tail = newNode;
        if (oldTail == null) {
            head = newNode;
        } else {
            oldTail.setNext(newNode);
        }
        size++;
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new QueueIsEmptyException(
                "[ERROR]: Queue is empty"
            );
        }
        Node<E> next = head.getNext();
        Node<E> currentNode = head;
        head = head.getNext();
        if (next == null) {
            tail = null;
        } else {
            next.setPrev(null);
        }
        E element = currentNode.getElement();
        currentNode.setElement(null);
        currentNode.setPrev(null);
        currentNode.setNext(null);
        size--;
        return element;
    }

    public E peek() {
        if (isEmpty()) {
            throw new QueueIsEmptyException(
                "[ERROR]: Queue is empty"
            );
        }
        return head.getElement();
    }

    public boolean isEmpty() {
        return head == null && tail == null;
    }

    public int size() {
        return size;
    }

    public void clear() {
        /*
        while (!isEmpty()) {
            dequeue();
        }
        */
        for (int i = 0; i < size; ++i) {
            Node<E> currentNode = head;
            head = head.getNext();
            currentNode.setElement(null);
            currentNode.setPrev(null);
            currentNode.setNext(null);
        }
        this.head = null;
        this.tail = this.head;
        this.size = 0;
    }

    private class Node<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;
        public Node() {
            this(null, null, null);
        }
        public Node(E element) {
            this(element, null, null);
        }
        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
        public E getElement() {
            return element;
        }
        public void setElement(E element) {
            this.element = element;
        }
        public Node<E> getPrev() {
            return prev;
        }
        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }
        public Node<E> getNext() {
            return next;
        }
        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
}
/* EOF */
```
> 代码清单：链表实现队列 - `Java`代码

<!-- EOF -->
