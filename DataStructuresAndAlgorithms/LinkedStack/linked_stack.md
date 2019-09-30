# 链表实现栈（Java）

栈（Stack）是一种后进先出（LIFO）的线性数据结构，具体实现上有：顺序栈、链式栈。

本文讲解在 Java 中使用链表实现链式栈。

# 实现思路

链表实现栈结构，底层数据结构选用双向链表，准备栈底变量`base`，栈顶指针`top`，引入一个`size`变量指示栈中已存放元素数量。

- 入栈：将元素包装为双向链表节点，尾插法插入到链表中，栈顶指针指向当前节点。

- 出栈：暂存栈顶指针所指节点元素，从链表中移除栈顶节点，栈顶指针指向其前驱节点。

# 链式栈测试代码（TDD）

秉持着测试驱动开发（TDD）原则，先编写链式栈测试代码。

```java
import Stack;
import StackIsFullException;
import StackIsEmptyException;
import LinkedStack;
public class LinkedStackTest {
    public static void main(String[] args) {
        int capacity = 128;
        Stack<Integer> linkedStack = new LinkedStack<Integer>();
        System.out.println("Before push elements, LinkedStack.size(): " +
            linkedStack.size()
        );
        for (int i = 0; i < capacity; ++i) {
            linkedStack.push(i);
        }
        System.out.println("After push elements, LinkedStack.size(): " +
            linkedStack.size()
        );
        System.out.println("LinkedStack.top(): " + linkedStack.top());
        while (!linkedStack.isEmpty()) {
            System.out.println(linkedStack.pop());
        }
        System.out.println("After pop elements, LinkedStack.size(): " +
            linkedStack.size()
        );
    }
}
/* EOF */
```
> 代码清单：链式栈测试代码（Java）

# 链式栈数据字段

理解链式栈整体实现思路后，着手编写 Java 代码：引入栈操作接口与栈异常，使用范型类来实现栈接口，这样栈可以存放任何类型的数据；维护双向链表节点内部类、栈底指针、栈顶指针、栈容量变量。

```java
import Stack;
import StackIsEmptyException;
import StackIsFullException;
public class LinkedStack<E> implements Stack<E> {
    /* 栈底指针 */
    private Node<E> base;
    /* 栈顶指针 */
    private Node<E> top;
    /* 栈已存放元素数量 */
    private int size;
    /* 双向链表节点内部类 */
    private class Node<E> {
        /* 实际元素 */
        private E element;
        /* 前驱节点 */
        private Node<E> prev;
        /* 后继节点 */
        private Node<E> next;
        /* 构造函数 */
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
        /* Getter & Setter */
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
> 代码清单：链式栈数据字段 - Java 代码

# 链式栈构造函数

链式栈一般无需限定栈容量，构造函数初始化过程：将栈底指针指向一个特殊的双向链表节点（链表头节点），栈顶指针也指向链表头节点，初始化栈容量。

```java
/**
 * 初始化链式栈。
 */
public LinkedStack() {
    this.base = new Node<E>();
    this.top = this.base;
    this.size = 0;
}
```
> 代码清单：链式栈构造函数 - Java 代码

# 链式栈压栈操作

压栈操作`push()`将指定元素压入栈中，在链式栈中其具体实现为：以传入元素构建一枚新的双向链表节点，使用尾插法插入新节点，栈顶指针指向新节点，栈存放元素数量增加1。

```java
/**
 * 将目标元素压入栈中。
 * @param e
 * @return void
 */
public void push(E e) {
    Node<E> newNode = new Node<E>(e);
    newNode.setNext(null);
    newNode.setPrev(top);
    top.setNext(newNode);
    top = newNode;
    size++;
}
```
> 代码清单：链式栈压栈操作 - Java 代码

# 链式栈弹栈操作

弹栈操作`pop()`将栈顶元素弹出栈，在链式栈中其具体实现为：先检查栈是否为空，栈为空则抛出异常，栈存放元素数量减少1，暂存栈顶节点，从链表中移除栈顶节点，堆栈指针前移一位，返回暂存节点元素。

```java
/**
 * 将栈顶元素弹出栈。
 * @param void
 * @return E
 */
public E pop() {
    if (isEmpty()) {
        throw new StackIsEmptyException("[ERROR]: Stack is empty");
    }
    Node<E> currentNode = top;
    top = top.getPrev();
    top.setNext(null);
    E element = currentNode.getElement();
    currentNode.setElement(null);
    currentNode.setPrev(null);
    currentNode.setNext(null);
    size--;
    return element;
}
```
> 代码清单：链式栈弹栈操作 - Java 代码

# 链式栈其他操作

实现链式栈其他一些操作，这些操作的实现都比较简单。

```java
/**
 * 查看栈顶元素。
 * @param void
 * @return E
 */
public E top() {
    if (isEmpty()) {
        throw new StackIsEmptyException("[ERROR]: Stack is empty");
    }
    return top.getElement();
}
/**
 * 清空栈。
 * @param void
 * @return void
 */
public void clear() {
    while (size > 0) {
        Node<E> currentNode = top;
        top = top.getPrev();
        top.setNext(null);
        currentNode.setElement(null);
        currentNode.setPrev(null);
        currentNode.setNext(null);
        size--;
    }
}
/**
 * 判断栈是否为空。
 * @param void
 * @return boolean
 */
public boolean isEmpty() {
    return size == 0;
}
/**
 * 获取栈已存放元素数量。
 * @param void
 * @return size
 */
public int size() {
    return size;
}
```
> 代码清单：链式栈其他操作 - Java 代码

# 完整代码

```java
import Stack;
import StackIsEmptyException;
import StackIsFullException;
public class LinkedStack<E> implements Stack<E> {
    private Node<E> base;
    private Node<E> top;
    private int size;
    public LinkedStack() {
        this.base = new Node<E>();
        this.top = this.base;
        this.size = 0;
    }
    public void push(E e) {
        Node<E> newNode = new Node<E>(e);
        newNode.setNext(null);
        newNode.setPrev(top);
        top.setNext(newNode);
        top = newNode;
        size++;
    }
    public E pop() {
        if (isEmpty()) {
            throw new StackIsEmptyException("[ERROR]: Stack is empty");
        }
        Node<E> currentNode = top;
        top = top.getPrev();
        top.setNext(null);
        E element = currentNode.getElement();
        currentNode.setPrev(null);
        currentNode.setNext(null);
        currentNode.setElement(null);
        size--;
        return element;
    }
    public E top() {
        if (isEmpty()) {
            throw new StackIsEmptyException("[ERROR]: Stack is empty");
        }
        return top.getElement();
    }
    public void clear() {
        while (size > 0) {
            Node<E> currentNode = top;
            top = top.getPrev();
            top.setNext(null);
            currentNode.setElement(null);
            currentNode.setPrev(null);
            currentNode.setNext(null);
            size--;
        }
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
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
> 代码清单：链表实现栈 - Java 代码

<!-- EOF -->
