package io.ziheng.queue;

import io.ziheng.queue.Queue;
import io.ziheng.queue.QueueIsFullException;
import io.ziheng.queue.QueueIsEmptyException;

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

    @Override
    public void enqueue(E e) {
        ensureCapacity();
        elementData[tail++] = e;
        size++;
    }

    @Override
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

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new QueueIsEmptyException("[ERROR]: Queue is empty");
        }
        return elementData[head];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
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
        E[] oldElementData = elementData;
        E[] newElementData = (E[])new Object[newCapacity];
        for (int i = head; i < tail; ++i) {
            newElementData[i] = oldElementData[i];
            oldElementData[i] = null;
        }
        elementData = newElementData;
    }
}
/* EOF */