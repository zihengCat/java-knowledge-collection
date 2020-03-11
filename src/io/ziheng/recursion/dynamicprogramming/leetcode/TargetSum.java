package io.ziheng.recursion.dynamicprogramming.leetcode;

import java.util.Map;
import java.util.HashMap;

/**
 * LeetCode 494. Target Sum
 * https://leetcode.com/problems/target-sum/
 */
public class TargetSum {
    private Map<String, Integer> memory = new HashMap<>();
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return findTargetSumWays0(0, nums.length, nums, S, 0);
    }
    private int findTargetSumWays0(int index, int length, int[] nums, int target, int sum) {
        if (index >= length) {
            if (sum == target) {
                return 1;
            } else {
                return 0;
            }
        }
        String pattern = index + "->" + sum;
        if (memory.containsKey(pattern)) {
            return memory.get(pattern);
        }
        int add = findTargetSumWays0(index + 1, length, nums, target, sum + nums[index]);
        int minus = findTargetSumWays0(index + 1, length, nums, target, sum - nums[index]);
        memory.put(pattern, add + minus);
        return memory.get(pattern);
    }
}
/* EOF */