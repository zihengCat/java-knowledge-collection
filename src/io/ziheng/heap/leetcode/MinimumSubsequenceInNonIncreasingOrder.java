package io.ziheng.heap.leetcode;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Collections;
import java.util.LinkedList;

/**
 * LeetCode 1403. Minimum Subsequence in Non-Increasing Order
 * https://leetcode.com/problems/minimum-subsequence-in-non-increasing-order/
 */
public class MinimumSubsequenceInNonIncreasingOrder {
    public static void main(String[] args) {
        // ...
    }
    public List<Integer> minSubsequence(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new LinkedList<>();
        }
        List<Integer> resultList = new LinkedList<>();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(
            Collections.reverseOrder()
        );
        int totalSum = 0;
        int subSum = 0;
        for (int n : nums) {
            priorityQueue.offer(n);
            totalSum += n;
        }
        while (subSum <= totalSum / 2) {
            int element = priorityQueue.poll();
            subSum += element;
            resultList.add(element);
        }
        return resultList;
    }
}
/* EOF */