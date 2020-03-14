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
        // memo = new int[nums.length];
        // Arrays.fill(memo, -1);
        // return robTopToBottom(0, nums.length, nums);
        return robBottomToTop(nums);
    }
    /**
     * 动态规划 -> 自底向上
     *
     * @param nums
     * @return int
     */
    private int robBottomToTop(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(
                nums[i] + dp[i - 2],
                dp[i - 1]
            );
        }
        return dp[nums.length - 1];
    }
    private int[] memo;
    /**
     * 动态规划 -> 自顶向下（记忆化搜索）
     *
     * @param i
     * @param n
     * @param nums
     * @return int
     */
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