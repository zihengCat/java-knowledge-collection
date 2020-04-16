package io.ziheng.string.leetcode;

import java.util.Set;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * LeetCode 316. Remove Duplicate Letters
 * https://leetcode.com/problems/remove-duplicate-letters/
 */
public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        Set<Character> aSet = new HashSet<>();
        PriorityQueue<Character> priorityQueue = new PriorityQueue<>();
        for (char c : s.toCharArray()) {
            aSet.add(c);
        }
        priorityQueue.addAll(aSet);
        StringBuilder stringBuilder = new StringBuilder();
        while (!priorityQueue.isEmpty()) {
            stringBuilder.append(priorityQueue.poll());
        }
        return stringBuilder.toString();
    }
}
/* EOF */