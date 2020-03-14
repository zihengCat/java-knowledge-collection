package io.ziheng.recursion.dynamicprogramming.leetcode;

import java.util.Arrays;

/**
 * 343. Integer Break
 * https://leetcode.com/problems/integer-break/
 */
public class IntegerBreak {
    public int integerBreak(int n) {
        if (n < 0) {
            return 0;
        }

        // return integerBreakRecursively(n);

        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return integerBreakTopToBottom(n);
    }
    /**
     * 动态递推。
     *
     * @param n
     * @return int
     */
    public int integerBreakV3(int n) {
        int[] dp = new int[n + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = -1;
        }
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            // 分割部分：j + (i - j)
            // 最优子结构：j * (i - j)
            // 已被计算出：
            // integerBreak(i - j) => memory[i - j]
            for (int j = 1; j < i; j++) {
                int oldResult = dp[i];
                dp[i] = Math.max(
                    oldResult,
                    Math.max(j * (i - j), dp[i - j])
                );
            }
        }
        return dp[n];
    }
    /**
     * 记忆化搜索
     *
     * @param n
     * @return int
     */
    private int[] memo;
    private int integerBreakTopToBottom(int n) {
        if (n == 1) {
            return 1;
        }
        if (memo[n] == -1) {
            int maxResult = Integer.MIN_VALUE;
            for (int i = 1; i < n; i++) {
                maxResult = Math.max(
                    i * (n - i),
                    Math.max(maxResult, i * integerBreakTopToBottom(n - i))
                );
            }
            memo[n] = maxResult;
        }
        return memo[n];
    }
    /**
     * 暴力递归
     *
     * @param n
     * @return int
     */
    public int integerBreakRecursively(int n) {
        if (n == 1) {
            return 1;
        }
        int maxResult = -1;
        for (int i = 1; i < n; i++) {
            maxResult = Math.max(maxResult, i * integerBreakRecursively(n - i));
            maxResult = Math.max(maxResult, i * (n - i));
        }
        return maxResult;
    }
}
/* EOF */