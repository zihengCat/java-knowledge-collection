package io.ziheng.hashtable.leetcode;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

/**
 * LeetCode 260. Single Number III
 * https://leetcode.com/problems/single-number-iii/
 */
public class SingleNumberIII {
    public static void main(String[] args) {
        // ...
    }
    /**
     * LeetCode 260. Single Number III
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param nums
     * @return int[]
     */
    public int[] singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        List<Integer> aList = new LinkedList<>();
        Map<Integer, Integer> aMap = new HashMap<>();
        for (int n : nums) {
            aMap.put(n, 1 + aMap.getOrDefault(n, 0));
        }
        for (Map.Entry<Integer, Integer> e : aMap.entrySet()) {
            if (e.getValue() == 1) {
                aList.add(e.getKey());
            }
        }
        int[] rArray = new int[aList.size()];
        for (int i = 0; i < aList.size(); i++) {
            rArray[i] = aList.get(i);
        }
        return rArray;
    }
}
/* EOF */