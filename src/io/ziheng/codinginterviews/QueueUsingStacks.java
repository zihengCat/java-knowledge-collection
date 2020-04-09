package io.ziheng.codinginterviews;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer 面试题 07：使用栈实现队列
 *
 * 题目描述：
 * 使用栈来实现一个队列，完成队列的 enqueue() 与 dequeue() 操作。
 * 队列中的元素为 int 类型。
 *
 * 知识点：["队列", "栈"]
 */
public class QueueUsingStacks {
    /**
     * 主函数 -> 测试用例
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        QueueUsingStacks queue = new QueueUsingStacks();
        int[] arr = new int[]{1, 2, 3, 4, 5, };
        for (int n : arr) {
            queue.enqueue(n);
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(queue.dequeue());
        }
    }
    Deque<Integer> primaryStack;
    Deque<Integer> secondaryStack;
    public QueueUsingStacks() {
        this.primaryStack = new LinkedList<>();
        this.secondaryStack = new LinkedList<>();
    }
    public void enqueue(int val) {
        primaryStack.push(val);
    }
    public int dequeue() {
        while (!primaryStack.isEmpty()) {
            secondaryStack.push(primaryStack.pop());
        }
        int result = secondaryStack.pop();
        // while (!secondaryStack.isEmpty()) {
        //     primaryStack.push(secondaryStack.pop());
        // }
        return result;
    }
}
/* EOF */