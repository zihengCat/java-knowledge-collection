package io.ziheng.recursion.dynamicprogramming.leetcode;
/**
 * LeetCode 300. Longest Increasing Subsequence
 * https://leetcode.com/problems/longest-increasing-subsequence/
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        if (nums == null) {
            return 0;
        }
        lengthOfLIS0(0, nums.length, nums);
        return 0;
    }
    private void lengthOfLIS0(int i, int n, int[] nums) {
        if (i >= n) {
            return;
        }

    }
}
/* EOF */