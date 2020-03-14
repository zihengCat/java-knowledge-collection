package io.ziheng.recursion.dynamicprogramming.leetcode;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

/**
 * LeetCode 152. Maximum Product Subarray
 * https://leetcode.com/problems/maximum-product-subarray/
 */
public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        if (nums == null) {
            return 0;
        }
        maxValue = nums[0];
        maxProduct0(1, nums.length, nums[0], nums);
        return maxValue;
    }
    /**
     * DP 解法
     *
     * @param nums
     * @return int
     */
    private int maxProductDP(int[] nums) {
        int[][] dp = new int[nums.length][2];
        int res = nums[0];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i][0] = Math.max(nums[i], Math.max(dp[i - 1][0] * nums[i], dp[i - 1][1] * nums[i]));
            dp[i][1] = Math.min(nums[i], Math.min(dp[i - 1][0] * nums[i], dp[i - 1][1] * nums[i]));
            res = Math.max(res, dp[i][0]);
        }
        return res;
    }
    int maxValue = Integer.MIN_VALUE;
    Map<String, Integer> memo = new HashMap<>();
    private void maxProduct0(int i, int n, int product, int[] nums) {
        if (i >= n) {
            return;
        }
        int maxNum = Math.max(nums[i], nums[i] * product);
        maxValue = Math.max(maxValue, maxNum);
        maxProduct0(i + 1, n, nums[i], nums);
        maxProduct0(i + 1, n, nums[i] * product, nums);
    }
}
/* EOF */