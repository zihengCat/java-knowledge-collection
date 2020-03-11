package io.ziheng.stack.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * LeetCode 232. Implement Queue using Stacks
 * https://leetcode.com/problems/implement-queue-using-stacks/
 */
public class ImplementQueueUsingStacks {

    public static void main(String[] args) {
        ImplementQueueUsingStacks queue = new ImplementQueueUsingStacks();
        int capacity = 10;
        for (int i = 0; i < capacity; i++) {
            queue.push(i);
        }
        while (!queue.empty()) {
            System.out.printf("%d, ", queue.pop());
        }
        System.out.println();
    }

    private Deque<Integer> primaryStack;
    private Deque<Integer> secondaryStack;

    /**
     * Initialize your data structure here.
     */
    public ImplementQueueUsingStacks() {
        this.primaryStack = new LinkedList<>();
        this.secondaryStack = new LinkedList<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        primaryStack.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        while (!primaryStack.isEmpty()) {
            secondaryStack.push(primaryStack.pop());
        }
        int ret = secondaryStack.pop();
        while (!secondaryStack.isEmpty()) {
            primaryStack.push(secondaryStack.pop());
        }
        return ret;
    }

    /**
     * Get the front element.
     */
    public int peek() {
        while (!primaryStack.isEmpty()) {
            secondaryStack.push(primaryStack.pop());
        }
        int ret = secondaryStack.peek();
        while (!secondaryStack.isEmpty()) {
            primaryStack.push(secondaryStack.pop());
        }
        return ret;
    }
    
    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return primaryStack.isEmpty() && secondaryStack.isEmpty();
    }

}
/* EOF */