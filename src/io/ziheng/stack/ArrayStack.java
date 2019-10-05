package io.ziheng.stack;

import io.ziheng.stack.Stack;
import io.ziheng.stack.StackIsEmptyException;
import io.ziheng.stack.StackIsFullException;

public class ArrayStack<E> implements Stack<E> {

    private static final int DEFAULT_CAPACITY = 16;
    private E[] elementData;
    private int top;
    private int size;

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }
    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(
                "[ERROR]: capacity is less than zero"
            );
        }
        this.elementData = (E[])new Object[capacity];
        this.top = 0;
        this.size = 0;
    }
    public void push(E e) {
        ensureCapacity();
        elementData[top++] = e;
        size++;
    }
    public E pop() {
        if (isEmpty()) {
            throw new StackIsEmptyException("[ERROR]: Stack is empty");
        }
        size--;
        return elementData[--top];
    }
    public E top() {
        if (isEmpty()) {
            throw new StackIsEmptyException("[ERROR]: Stack is empty");
        }
        return elementData[top - 1];
    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public void clear() {
        for (int i = 0; i < size; ++i) {
            elementData[i] = null;
        }
        top = 0;
        size = 0;
    }
    private void ensureCapacity() {
        if (size + 1 > elementData.length) {
            // throw new StackIsFullException("[ERROR]: Stack is full");
            grow();
        }
    }
    @SuppressWarnings("unchecked")
    private void grow() {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity << 1;
        E[] oldElementData = this.elementData;
        E[] newElementData = (E[])new Object[newCapacity];
        for (int i = 0; i < oldElementData.length; ++i) {
            newElementData[i] = oldElementData[i];
            oldElementData[i] = null;
        }
        this.elementData = newElementData;
    }
}
/* EOF */
