package io.ziheng.recursion.dynamicprogramming.leetcode;
/**
 * LeetCode 70. Climbing Stairs
 * https://leetcode.com/problems/climbing-stairs/
 */
public class ClimbingStairs {
    /**
     * 跳台阶 - 自底向上动态规划
     *
     * @param n
     * @return int
     */
    public int climbStairsWithBottomToTop(int n) {
        if (n < 0) {
            return 0;
        }
        int[] memory = new int[n + 1];
        for (int i = 0; i <= memory.length; i++) {
            memory[i] = -1;
        }
        memory[0] = 1;
        memory[1] = 1;
        for (int i = 2; i < n; i++) {
            memory[i] = memory[i - 1] + memory[i - 2];
        }
        return memory[n];
    }
    /**
     * 跳台阶 - 自顶向下记忆化搜索
     *
     * @param n
     * @return int
     */
    public int climbStairsWithTopToBottom(int n) {
        if (n < 0) {
            return 0;
        }
        int[] memory = new int[n + 1];
        for (int i = 0; i < memory.length; i++) {
            memory[i] = -1;
        }
        return climbStairsWithTopToBottom0(n, memory);
    }
    private int climbStairsWithTopToBottom0(int n, int[] memory) {
        if (n == 0 || n == 1) {
            return 1;
        }
        if (memory[n] == -1) {
            memory[n] = climbStairsWithTopToBottom0(n - 1, memory)
            + climbStairsWithTopToBottom0(n - 2, memory);
        }
        return memory[n];
    }
    /**
     * 跳台阶 - 标准递归解法（Time Limit Exceeded）
     *
     * @param n
     * @return int
     */
    public int climbStairs(int n) {
        if (n < 0) {
            return 0;
        }
        return climbStairs0(n);
    }
    private int climbStairs0(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return climbStairs0(n - 1) + climbStairs0(n - 2);
    }
}
/* EOF */