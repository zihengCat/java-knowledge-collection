package io.ziheng.queue;

import io.ziheng.queue.Queue;
import io.ziheng.queue.QueueIsFullException;
import io.ziheng.queue.QueueIsEmptyException;

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.LinkedList;

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
