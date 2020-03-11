package io.ziheng.queue.leetcode;

import java.util.Queue;
import java.util.LinkedList;

/**
 * LeetCode 225. Implement Stack using Queues
 * https://leetcode.com/problems/implement-stack-using-queues/
 */
public class ImplementStackUsingQueues {

    public static void main(String[] args) {
        ImplementStackUsingQueues stack = new ImplementStackUsingQueues();
        int capacity = 10;
        for (int i = 0; i < capacity; i++) {
            stack.push(i);
        }
        while (!stack.empty()) {
            System.out.printf("%d, ", stack.pop());
        }
        System.out.println();
    }

    private Queue<Integer> primaryQueue;
    private Queue<Integer> secondaryQueue;

    /**
     * Initialize your data structure here.
     */
    public ImplementStackUsingQueues() {
        this.primaryQueue = new LinkedList<>();
        this.secondaryQueue = new LinkedList<>();
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        primaryQueue.offer(x);
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        while (primaryQueue.size() > 1) {
            secondaryQueue.offer(primaryQueue.poll());
        }
        int ret = primaryQueue.poll();
        while (!secondaryQueue.isEmpty()) {
            primaryQueue.offer(secondaryQueue.poll());
        }
        return ret;
    }

    /**
     * Get the top element.
     */
    public int top() {
        while (primaryQueue.size() > 1) {
            secondaryQueue.offer(primaryQueue.poll());
        }
        int ret = primaryQueue.peek();
        secondaryQueue.offer(primaryQueue.poll());
        while (!secondaryQueue.isEmpty()) {
            primaryQueue.offer(secondaryQueue.poll());
        }
        return ret;
    }
    
    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return primaryQueue.isEmpty() && secondaryQueue.isEmpty();
    }

}
/* EOF */