package io.ziheng.heap.leetcode;

import java.util.PriorityQueue;

/**
 * LeetCode 215. Kth Largest Element in an Array
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 */
public class KthLargestElementInAnArray {
    private PriorityQueue<Integer> priorityQueue;
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || k > nums.length) {
            return 0;
        }
        this.priorityQueue = new PriorityQueue<>(k);
        return findKthLargest0(nums, k);
    }
    private int findKthLargest0(int[] nums, int k) {
        for (int num : nums) {
            if (priorityQueue.size() < k) {
                priorityQueue.offer(num);
            } else if (num > priorityQueue.peek()) {
                priorityQueue.poll();
                priorityQueue.offer(num);
            }
        }
        return priorityQueue.peek();
    }
}
/* EOF */