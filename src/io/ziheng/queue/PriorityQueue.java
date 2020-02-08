package io.ziheng.queue;

import io.ziheng.queue.Queue;
import io.ziheng.heap.HeapOperator;
import io.ziheng.heap.MaxHeap;

import java.util.Random;

public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private HeapOperator<E> heapOperator;

    public PriorityQueue() {
        this.heapOperator = new MaxHeap<E>();
    }

    public static void main(String[] args) {
        int capacity = 100;
        Random random = new Random();
        Queue<Integer> priorityQueue = new PriorityQueue<Integer>();
        for (int i = 0; i < capacity; i++) {
            priorityQueue.enqueue(random.nextInt(100));
        }
        assert priorityQueue.size() == capacity;
        int[] arr = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            arr[i] = priorityQueue.dequeue();
        }
        assert priorityQueue.isEmpty() == true;
        for (int i = 1; i < capacity; i++) {
            assert arr[i] <= arr[i - 1];
        }
        System.out.println("PriorityQueue test result: OK");
    }

    @Override
    public void enqueue(E e) {
        heapOperator.add(e);
    }

    @Override
    public E dequeue() {
        return heapOperator.extract();
    }

    @Override
    public E peek() {
        return heapOperator.peek();
    }

    @Override
    public int size() {
        return heapOperator.size();
    }

    @Override
    public boolean isEmpty() {
        return heapOperator.isEmpty();
    }

    @Override
    public void clear() {
        heapOperator.clear();
    }
}
/* EOF */