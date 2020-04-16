package io.ziheng.recursion.dynamicprogramming.leetcode;
/**
 * LeetCode 509. Fibonacci Number
 * https://leetcode.com/problems/fibonacci-number/
 */
public class FibonacciNumber {
    public int fib(int N) {
        if (N < 0) {
            return -1;
        }
        int[] dp = new int[N + 2];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[N];
    }
}
/* EOF */