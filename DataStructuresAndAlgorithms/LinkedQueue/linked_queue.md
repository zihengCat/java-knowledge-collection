# 链表实现队列（Queue）

...

# 实现思路

...

# 链式队列测试代码（TDD）

...

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
