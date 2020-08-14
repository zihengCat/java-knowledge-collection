package io.ziheng.recursion.backtracking.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 1286. Iterator for Combination
 * https://leetcode.com/problems/iterator-for-combination/
 */
public class IteratorForCombination {
    /**
     * Your CombinationIterator object will be instantiated
     * and called as such:
     */
    public static void main(String[] args) {
        String characters = "abc";
        int combinationLength = 2;
        CombinationIterator obj = new CombinationIterator(characters, combinationLength);
        String p1 = obj.next();
        boolean p2 = obj.hasNext();
        System.out.println(p1);
        System.out.println(p2);
    }
}
class CombinationIterator {
    private Queue<String> aQueue;
    public CombinationIterator(String characters, int combinationLength) {
        this.aQueue = new LinkedList<>();
        doCombination(characters, "", 0, combinationLength);
    }
    private void doCombination(String s, String prev, int idx, int k) {
        if (k == 0) {
            aQueue.offer(prev);
            return;
        }
        for (int i = idx; i < s.length(); i++) {
            // `i + 1` not `idx + 1`
            doCombination(s, prev + s.charAt(i), i + 1, k - 1);
        }
    }
    public String next() {
        return this.aQueue.poll();
    }
    public boolean hasNext() {
        return !this.aQueue.isEmpty();
    }
}
/* EOF */