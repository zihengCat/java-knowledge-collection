package io.ziheng.heap.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LeetCode 451. Sort Characters By Frequency
 * https://leetcode.com/problems/sort-characters-by-frequency/
 */
public class SortCharactersByFrequency {
    public String frequencySort(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char[] charArray = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : charArray) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, 1 + map.get(c));
            }
        }
       PriorityQueue<Map.Entry<Character, Integer>> priorityQueue = new PriorityQueue<>(
           new Comparator<Map.Entry<Character, Integer>>() {
                @Override
                public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                    return o2.getValue() - o1.getValue();
                }
            }
        );
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            priorityQueue.offer(entry);
        }
        StringBuilder sb = new StringBuilder();
        while (!priorityQueue.isEmpty()) {
            Map.Entry<Character, Integer> entry = priorityQueue.poll();
            char character = entry.getKey();
            int frequency = entry.getValue();
            for (int i = 0; i < frequency; i++) {
                sb.append(character);
            }
        }
        return sb.toString();
    }
}
/* EOF */