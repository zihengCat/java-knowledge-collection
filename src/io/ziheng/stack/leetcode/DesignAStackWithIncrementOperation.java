package io.ziheng.stack.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * LeetCode 1381. Design a Stack With Increment Operation
 * https://leetcode.com/problems/design-a-stack-with-increment-operation/
 */
public class DesignAStackWithIncrementOperation {

    private int maxSize;
    private int currentSize;
    private Deque<Integer> primaryStack;
    private Deque<Integer> secondaryStack;

    public DesignAStackWithIncrementOperation(int maxSize) {
        this.maxSize = maxSize;
        this.currentSize = 0;
        this.primaryStack = new LinkedList<>();
        this.secondaryStack = new LinkedList<>();
    }
    
    public void push(int x) {
        if (currentSize < maxSize) {
            primaryStack.push(x);
            currentSize++;
        }
    }

    public int pop() {
        if (currentSize != 0) {
            currentSize--;
            return primaryStack.pop();
        } else {
            return -1;
        }
    }
    
    public void increment(int k, int val) {
        /**
         * currentSize = 5
         * k = 2
         * 0 -> 3 = 3
         * 0 -> 2 = 2
         */
        if (k > currentSize) {
            k = currentSize;
        }
        for (int i = 0; i < currentSize - k; i++) {
            secondaryStack.push(primaryStack.pop());
        }
        for (int i = 0; i < k; i++) {
            secondaryStack.push(val + primaryStack.pop());
        }
        while (!secondaryStack.isEmpty()) {
            primaryStack.push(secondaryStack.pop());
        }
    }
}
/* EOF */