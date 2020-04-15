package io.ziheng.recursion.dynamicprogramming.leetcode;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

/**
 * LeetCode 300. Longest Increasing Subsequence
 * https://leetcode.com/problems/longest-increasing-subsequence/
 */
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        LongestIncreasingSubsequence obj = new LongestIncreasingSubsequence();
        int[] nums = {
            3, 5, 6, 2, 5, 4, 19, 5, 6, 7, 12,
        };
        System.out.println(
            obj.lengthOfLIS(nums)
        );
    }
    public int lengthOfLIS(int[] nums) {
        if (nums == null) {
            return 0;
        }

        // 暴力递归
        // return lengthOfLISBruteForceRecursion(i, n, prev, nums);

        // 记忆化搜索
        // -> Using Array
        // int[] memo = new int[nums.length + 1];
        // Arrays.fill(memo, -1);
        // return lengthOfLISTopToBottom(0, nums.length, Integer.MIN_VALUE, nums, memo);
        // -> Using HashMap
        return lengthOfLISTopToBottom(0, nums.length, Integer.MIN_VALUE, nums);

        // 动态规划
        // ...

    }

    // private int[][] memo;
    private Map<String, Integer> memo = new HashMap<>();
    /**
     * 自顶向下 -> 记忆化搜索
     *
     * @param i
     * @param n
     * @param prevNum
     * @param nums
     * @return int
     */
    private int lengthOfLISTopToBottom(int i, int n, int prevNum, int[] nums) {
        if (i >= n) {
            return 0;
        }
        String key = i + "->" + prevNum;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int taken = 0;
        int notTaken = 0;
        if (nums[i] > prevNum) {
            taken = 1 + lengthOfLISTopToBottom(i + 1, n, nums[i], nums);
        }
        notTaken = lengthOfLISTopToBottom(i + 1, n, prevNum, nums);
        memo.put(key, Math.max(taken, notTaken));
        return memo.get(key);
    }

    /**
     * 暴力递归
     *
     * @param i
     * @param n
     * @param prev
     * @param nums
     * @return int
     */
    private int lengthOfLISBruteForceRecursion(int i, int n, int prev, int[] nums) {
        if (i >= n) {
            return 0;
        }
        int taken = 0;
        int notTaken = 0;
        if (nums[i] > prev) {
            taken = 1 + lengthOfLISBruteForceRecursion(i + 1, n, nums[i], nums);
        }
        notTaken = lengthOfLISBruteForceRecursion(i + 1, n, prev, nums);
        return Math.max(taken, notTaken);
    }
}
/* EOF */