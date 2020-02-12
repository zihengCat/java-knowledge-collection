package io.ziheng.recursion.dynamicprogramming.leetcode;
/**
 * 343. Integer Break
 * https://leetcode.com/problems/integer-break/
 */
public class IntegerBreak {
    /**
     * 动态规划（自底向上）。
     *
     * @param n
     * @return int
     */
    public int integerBreakV3(int n) {
        assert n > 1;
        int[] memory = new int[n + 1];
        for (int i = 0; i < memory.length; i++) {
            memory[i] = -1;
        }
        memory[1] = 1;
        for (int i = 2; i <= n; i++) {
            /**
             * 分割部分：j + (i - j)
             * 最优子结构：j * (i - j)
             * 已被计算出：
             * integerBreak(i - j) => memory[i - j]
             */
            for (int j = 1; j < i; j++) {
                int oldResult = memory[i];
                memory[i] = Math.max(
                    oldResult,
                    Math.max(j * (i - j), memory[i - j])
                );
            }
        }
        return memory[n];
    }
    private int[] memory;
    /**
     * 动态规划（自顶向下）。
     *
     * @param n
     * @return int
     */
    public int integerBreakV2(int n) {
        memory = new int[n + 1];
        for (int i = 0; i < memory.length; i++) {
            memory[i] = -1;
        }
        return integerBreak0(n);
    }
    /**
     * 将 n 分割（至少2部分），获取分割最大乘积。
     */
    private int integerBreak0(int n) {
        if (n == 1) {
            return 1;
        }
        if (memory[n] != -1) {
            return memory[n];
        }
        /**
         * i + (n - i)
         */
        int maxResult = -1;
        for (int i = 1; i <= n - 1; i++) {
            maxResult = Math.max(maxResult, i * integerBreak0(n - i));
            maxResult = Math.max(maxResult, i * (n - i));
        }
        memory[n] = maxResult;
        return maxResult;
    }
    /**
     * 暴力递归 -> Time Limit Exceeded
     *
     * @param n
     * @return int
     */
    public int integerBreakV1(int n) {
        // i * (n - i)
        if (n == 1) {
            return 1;
        }
        int maxResult = -1;
        for (int i = 1; i < n; i++) {
            maxResult = Math.max(maxResult, i * integerBreakV1(n - i));
            maxResult = Math.max(maxResult, i * (n - i));
        }
        return maxResult;
    }
}
/* EOF */