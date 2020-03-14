package io.ziheng.recursion.dynamicprogramming.leetcode;

import java.util.Arrays;

/**
 * LeetCode 198. House Robber
 * https://leetcode.com/problems/house-robber/
 */
public class HouseRobber {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return robTopToBottom(0, nums.length, nums);
    }
    private int[] memo;
    private int robTopToBottom(int i, int n, int[] nums) {
        if (i >= n) {
            return 0;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        memo[i] = Math.max(
            nums[i] + robTopToBottom(i + 2, n, nums),
            0 + robTopToBottom(i + 1, n, nums)
        );
        return memo[i];
    }
}
/* EOF */