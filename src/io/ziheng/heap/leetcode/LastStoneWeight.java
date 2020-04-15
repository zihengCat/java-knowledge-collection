package io.ziheng.heap.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LeetCode 1046. Last Stone Weight
 * https://leetcode.com/problems/last-stone-weight/
 */
public class LastStoneWeight {
    public int lastStoneWeight(int[] stones) {
        if (stones == null || stones.length == 0) {
            return 0;
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(
            new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2.intValue() - o1.intValue();
                }
            }
        );
        for (int n : stones) {
            priorityQueue.offer(n);
        }
        while (priorityQueue.size() > 1) {
            int y = priorityQueue.poll();
            int x = priorityQueue.poll();
            if (x != y) {
                priorityQueue.offer(y - x);
            }
        }
        return priorityQueue.isEmpty() ? 0 : priorityQueue.poll();
    }
}
/* EOF */