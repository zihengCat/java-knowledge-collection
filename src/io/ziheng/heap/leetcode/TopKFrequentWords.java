package io.ziheng.heap.leetcode;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * LeetCode 692. Top K Frequent Words
 * https://leetcode.com/problems/top-k-frequent-words/
 */
public class TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        if (words == null || words.length == 0 || k < 0) {
            return new LinkedList<>();
        }
        // 1. Build frequent Map
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            if (!map.containsKey(word)) {
                map.put(word, 1);
            } else {
                map.put(word, 1 + map.get(word));
            }
        }
        // 1. Sort by frequent
        PriorityQueue<Map.Entry<String, Integer>> priorityQueue = new PriorityQueue<>(
            new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o2.getValue().equals(o1.getValue())
                        ? o1.getKey().compareTo(o2.getKey())
                        : o2.getValue() - o1.getValue();
                }
            }
        );
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            priorityQueue.offer(entry);
        }
        // 3. Build result list
        List<String> resultList = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            Map.Entry<String, Integer> entry = priorityQueue.poll();
            resultList.add(entry.getKey());
        }
        return resultList;
    }
}
/* EOF */