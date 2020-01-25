package io.ziheng.queue;

import org.omg.CORBA.SystemException;

import io.ziheng.queue.Queue;
import io.ziheng.queue.QueueIsFullException;
import io.ziheng.queue.QueueIsEmptyException;

public class CircularQueue<E> implements Queue<E> {

    private static final int DEFAULT_CAPACITY = 1 << 4;
    private E[] elementData;
    private int head;
    private int tail;
    private int size;
    private int capacity;

    public CircularQueue() {
        this(DEFAULT_CAPACITY + 1);
    }

    @SuppressWarnings("unchecked")
    public CircularQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(
                "[ERROR]: Queue capacity should be greater than zero."
            );
        }
        this.elementData = (E[])new Object[capacity + 1];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
        this.capacity = elementData.length;
    }

    public static void main(String[] args) {
        CircularQueue<Integer> circularQueue =
            new CircularQueue<Integer>(128);
        for (int i = 0; !circularQueue.isFull(); i++) {
            circularQueue.enqueue(i);
        }
        circularQueue.clear();
        int i = 0;
        while (!circularQueue.isFull()) {
            circularQueue.enqueue(i);
        }
        System.out.println("i = " + i);
        System.out.println("size: " + circularQueue.size());
    }

    @Override
    public void enqueue(E e) {
        if (isFull()) {
            throw new QueueIsFullException();
        }
        elementData[tail] = e;
        tail = (tail + 1) % capacity;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new QueueIsEmptyException();
        }
        E element = elementData[head];
        elementData[head] = null;
        head = (head + 1) % capacity;
        size--;
        return element;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new QueueIsEmptyException();
        }
        return elementData[head];
    }

    @Override
    public void clear() {
        for (int i = 0; i < capacity; ++i) {
            elementData[i] = null;
        }
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    public boolean isFull() {
        return (tail + 1) % capacity == head;
    }

    public int getCapacity() {
        return elementData.length;
    }

}
/* EOF */