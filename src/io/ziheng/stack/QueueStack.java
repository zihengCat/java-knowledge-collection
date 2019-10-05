package io.ziheng.stack;

import io.ziheng.stack.Stack;
import io.ziheng.stack.StackIsEmptyException;
import io.ziheng.stack.StackIsFullException;

import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayDeque;

public class QueueStack<E> implements Stack<E> {

    private Queue<E> primaryQueue;
    private Queue<E> secondaryQueue;

    public QueueStack() {
        this.primaryQueue = new ArrayDeque<E>();
        this.secondaryQueue = new LinkedList<E>();
    }
    public void push(E e) {
        primaryQueue.offer(e);
    }
    public E pop() {
        if (isEmpty()) {
            throw new StackIsEmptyException("[ERROR]: Stack is empty");
        }
        while (primaryQueue.size() > 1) {
            secondaryQueue.offer(
                primaryQueue.poll()
            );
        }
        E element = primaryQueue.poll();
        while (!secondaryQueue.isEmpty()) {
            primaryQueue.offer(
                secondaryQueue.poll()
            );
        }
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
