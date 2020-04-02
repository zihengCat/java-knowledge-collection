package io.ziheng.heap.leetcode;

import java.util.PriorityQueue;

/**
 * LeetCode 378. Kth Smallest Element in a Sorted Matrix
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 */
public class KthSmallestElementInASortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || k < 0) {
            return 0;
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                priorityQueue.offer(matrix[i][j]);
            }
        }
        int result = 0;
        for (int i = 0; i < k; i++) {
            result = priorityQueue.poll();
        }
        return result;
    }
}
/* EOF */