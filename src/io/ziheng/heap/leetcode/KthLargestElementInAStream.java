package io.ziheng.heap.leetcode;

import java.util.PriorityQueue;

/**
 * LeetCode 703. Kth Largest Element in a Stream
 * https://leetcode.com/problems/kth-largest-element-in-a-stream/
 */
public class KthLargestElementInAStream {
    private PriorityQueue<Integer> priorityQueue;
    private int k;
    public KthLargestElementInAStream(int k, int[] nums) {
        this.k = k;
        this.priorityQueue = new PriorityQueue<>(k);
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (priorityQueue.size() < k) {
            priorityQueue.offer(val);
        } else if (val > priorityQueue.peek()) {
            priorityQueue.poll();
            priorityQueue.offer(val);
        }
        return priorityQueue.peek();
    }
}
/* EOF */