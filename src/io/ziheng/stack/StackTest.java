package io.ziheng.stack;

import io.ziheng.stack.Stack;
import io.ziheng.stack.StackIsFullException;
import io.ziheng.stack.StackIsEmptyException;

import io.ziheng.stack.ArrayStack;
import io.ziheng.stack.LinkedStack;
import io.ziheng.stack.QueueStack;

public class StackTest {
    public static void main(String[] args) {
        testStack(new ArrayStack<Integer>());
        testStack(new LinkedStack<Integer>());
        testStack(new QueueStack<Integer>());
    }
    public static void testStack(Stack<Integer> stack) {
        int capacity = 128;
        stack.clear();
        System.out.println("Before push elements, Stack.size(): " +
            stack.size()
        );
        for (int i = 0; i < capacity; ++i) {
            stack.push(i);
        }
        System.out.println("After push elements, Stack.size(): " +
            stack.size()
        );
        System.out.println("Stack.top(): " + stack.top());
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
        System.out.println("After pop elements, Stack.size(): " +
            stack.size()
        );
    }
}
/* EOF */
