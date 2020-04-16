package io.ziheng.recursion.dynamicprogramming.leetcode;
/**
 * LeetCode 1137. N-th Tribonacci Number
 * https://leetcode.com/problems/n-th-tribonacci-number/
 */
public class NthTribonacciNumber {
    public int tribonacci(int n) {
        int[] dp = new int[n + 3];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return dp[n];
    }
}
/* EOF */