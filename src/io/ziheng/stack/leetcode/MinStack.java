package io.ziheng.stack.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * LeetCode 155. Min Stack
 * https://leetcode.com/problems/min-stack/
 */
public class MinStack {

    private Deque<Integer> normalStack;
    private Deque<Integer> minStack;

    public MinStack() {
        this.normalStack = new LinkedList<>();
        this.minStack = new LinkedList<>();
    }

    public void push(int x) {
        normalStack.push(x);
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }

    public void pop() {
        if (normalStack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        normalStack.pop();
    }

    public int top() {
        return normalStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

}
/* EOF */