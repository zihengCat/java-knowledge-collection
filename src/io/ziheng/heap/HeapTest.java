package io.ziheng.heap;

import io.ziheng.heap.HeapOperation;
import io.ziheng.heap.MaxHeap;
import java.util.Random;

public class HeapTest {
    public static void main(String[] args) {
        testHeap();
    }
    public static void testHeap() {
        int capacity = 1000000;
        Random random = new Random();
        HeapOperation<Integer> maxHeap = new MaxHeap<Integer>(capacity);
        for (int i = 0; i < capacity; ++i) {
            maxHeap.add(
                random.nextInt(Integer.MAX_VALUE)
            );
        }
        assert maxHeap.size() == capacity;
        int[] arr = new int[capacity];
        for (int i = 0; i < capacity; ++i) {
            arr[i] = maxHeap.extract();
        }
        for (int i = 1; i < capacity; ++i) {
            assert arr[i] <= arr[i - 1];
        }
        assert maxHeap.size() == 0;
        maxHeap = new MaxHeap<Integer>(new Integer[]{1, 1, 2, 3, 4, 5, });
        assert maxHeap.toString().equals("[5, 4, 2, 3, 1, 1, ]");
        System.out.println("Test result: OK");
    }
}
/* EOF */