package io.ziheng.queue;

import io.ziheng.queue.Queue;
import io.ziheng.queue.ArrayQueue;
import io.ziheng.queue.CircularQueue;
//import io.ziheng.queue.LinkedQueue;
import io.ziheng.queue.StackQueue;
import io.ziheng.queue.PriorityQueue;

public class QueueTest {
    public static void main(String[] args) {
        testQueue(new ArrayQueue<Integer>());
        //testQueue(new LinkedQueue<Integer>());
        testQueue(new CircularQueue<Integer>(128));
        testQueue(new StackQueue<Integer>());
        testQueue(new PriorityQueue<Integer>());
    }
    public static void testQueue(Queue<Integer> queue) {
        System.out.println("---------------------------------------");
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
            queue.dequeue();
        }
        System.out.println("After dequeue elements, Queue.size(): " +
            queue.size()
        );
        System.out.println("---------------------------------------");
    }
}
/* EOF */