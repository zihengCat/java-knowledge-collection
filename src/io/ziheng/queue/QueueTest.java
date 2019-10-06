package io.ziheng.queue;

import io.ziheng.queue.Queue;
import io.ziheng.queue.ArrayQueue;

public class QueueTest {
    public static void main(String[] args) {
        testQueue(new ArrayQueue<Integer>());
    }
    public static void testQueue(Queue<Integer> queue) {
        int capacity = 128;
        queue.clear();
        System.out.println("Before enqueue elements, Queue.size(): " +
            queue.size()
        );
        for (int i = 0; i < capacity; ++i) {
            queue.enqueue(i);
        }
        System.out.println("After enqueue elements, Queue.size(): " +
            queue.size()
        );
        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
        System.out.println("After dequeue elements, Queue.size(): " +
            queue.size()
        );
    }
}
/* EOF */
