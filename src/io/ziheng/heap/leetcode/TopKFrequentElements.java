package io.ziheng.heap.leetcode;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * LeetCode 347. Top K Frequent Elements
 * https://leetcode.com/problems/top-k-frequent-elements/
 */
public class TopKFrequentElements {
    /**
     * 主函数 -> 测试用例
     */
    public static void main(String[] args) {
        TopKFrequentElements obj = new TopKFrequentElements();
        int[] nums = new int[]{-1, -1, };
        int k = 1;
        System.out.println(
            obj.topKFrequent(nums, k)
        );
        nums = new int[]{1,1,1,2,2,3};
        k = 2;
        System.out.println(
            obj.topKFrequent(nums, k)
        );
    }
    private class Pair<L, R> {
        public L left;
        public R right;
        public Pair(L left, R right) {
            this.left = left;
            this.right = right;
        }
    }
    public List<Integer> topKFrequent(int[] nums, int k) {
        if (nums == null || k < 0) {
            return new LinkedList<>();
        }
        List<Integer> resultList = new LinkedList<>();
        PriorityQueue<Pair<Integer, Integer>> priorityQueue =
            new PriorityQueue<>(
                new Comparator<Pair<Integer, Integer>>() {
                    @Override
                    public int compare(
                        Pair<Integer, Integer> o1,
                        Pair<Integer, Integer> o2) {
                        return o2.right - o1.right;
                    }
                }
            );
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, 1 + map.get(num));
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            priorityQueue.offer(
                new Pair<Integer, Integer>(entry.getKey(), entry.getValue())
            );
        }
        for (int i = 0; i < k; i++) {
            Pair<Integer, Integer> pair = priorityQueue.poll();
            resultList.add(
                pair.left
            );
        }
        return resultList;
    }
}
/* EOF */