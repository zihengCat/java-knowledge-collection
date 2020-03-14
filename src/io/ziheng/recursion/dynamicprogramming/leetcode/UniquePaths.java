package io.ziheng.recursion.dynamicprogramming.leetcode;

import java.util.Arrays;

/**
 * LeetCode 62. Unique Paths
 * https://leetcode.com/problems/unique-paths/
 */
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        // 暴力递归
        // return uniquePathsRecursively(0, 0, m, n);

        // 记忆化搜索
        memo = new int[n + 1][m + 1];
        for (int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i], -1);
        }
        return uniquePathsTopToBottom(0, 0, m, n);

        // 动态递推
        // ...
    }
    private int[][] memo;
    /**
     * 记忆化搜索
     *
     * @param i
     * @param j
     * @param m
     * @param n
     * @return int
     */
    private int uniquePathsTopToBottom(int i, int j, int m, int n) {
        if (!isVaild(i, j, m, n)) {
            return 0;
        }
        if (i == n - 1 && j == m - 1) {
            return 1;
        }
        if (memo[i][j] == -1) {
            memo[i][j] = uniquePathsTopToBottom(i + 1, j, m, n)
                + uniquePathsTopToBottom(i, j + 1, m, n);
        }
        return memo[i][j];
    }
    /**
     * 暴力递归
     *
     * @param i
     * @param j
     * @param m
     * @param n
     * @return int
     */
    private int uniquePathsRecursively(int i, int j, int m, int n) {
        if (!isVaild(i, j, m, n)) {
            return 0;
        }
        if (i == n - 1 && j == m - 1) {
            return 1;
        }
        int downPathNum = uniquePathsRecursively(i + 1, j, m, n);
        int rightPathNum = uniquePathsRecursively(i, j + 1, m, n);
        return downPathNum + rightPathNum;
    }
    /**
     * 当前位置合法性判断
     *
     * @param i
     * @param j
     * @param m
     * @param n
     * @return boolean
     */
    private boolean isVaild(int i, int j, int m, int n) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }
}
/* EOF */