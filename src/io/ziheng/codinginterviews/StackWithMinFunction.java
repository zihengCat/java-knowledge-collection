package io.ziheng.codinginterviews;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer 面试题 30：包含 min() 函数的栈
 *
 * 题目描述：
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的 min() 函数。
 * - 时间复杂度应为 O(1) 。
 * 注意：保证测试中不会当栈为空的时候，对栈调用 pop()，top()，min() 方法。
 *
 * 知识点：["栈"]
 */
public class StackWithMinFunction {
    /**
     * 主函数 -> 测试用例
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 0, 1, };
        StackWithMinFunction stack = new StackWithMinFunction();
        for (int n : arr) {
            stack.push(n);
        }
        for (int i = 0; i < arr.length - 1; i++) {
            stack.pop();
            System.out.println(
                "stack.min(): "
                + stack.min()
            );
        }
    }

    private Deque<Integer> primaryStack;
    private Deque<Integer> minStack;

    public StackWithMinFunction() {
        this.primaryStack = new LinkedList<>();
        this.minStack = new LinkedList<>();
    }

    public void push(int val) {
        primaryStack.push(val);
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    public void pop() {
        if (primaryStack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        primaryStack.pop();
    }

    public int top() {
        return primaryStack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}
/* EOF */