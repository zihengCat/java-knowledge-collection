package io.ziheng.queue;

import io.ziheng.queue.Queue;
import io.ziheng.queue.ArrayQueue;

public class QueueTest {
    public static void main(String[] args) {
        testQueue(new ArrayQueue<Integer>());
    }
    public static void testQueue(Queue<Integer> queue) {
        /* test Queue.clear() */
        queue.enqueue(0);
        System.out.println("Before clear elements, Queue.size(): " +
            queue.size()
        );
        queue.clear();
        System.out.println("After clear elements, Queue.size(): " +
            queue.size()
        );
        /* test Queue.enqueue() */
        int capacity = 128;
        System.out.println("Before enqueue elements, Queue.size(): " +
            queue.size()
        );
        for (int i = 0; i < capacity; ++i) {
            queue.enqueue(i);
        }
        System.out.println("After enqueue elements, Queue.size(): " +
            queue.size()
        );
        /* test Queue.dequeue() */
        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
        System.out.println("After dequeue elements, Queue.size(): " +
            queue.size()
        );
    }
}
/* EOF */
