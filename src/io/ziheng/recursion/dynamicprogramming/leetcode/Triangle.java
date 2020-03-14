package io.ziheng.recursion.dynamicprogramming.leetcode;

import java.util.List;

/**
 * LeetCode 120. Triangle
 * https://leetcode.com/problems/triangle/
 */
public class Triangle {

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }

        // return minimumTotalRecursively(0, 0, triangle.size(), triangle);

        // memo = new int[triangle.size()][triangle.size()];
        // for (int i = 0; i < memo.length; i++) {
        //     for (int j = 0; j < memo[i].length; j++) {
        //         memo[i][j] = -1;
        //     }
        // }
        // return minimumTotalTopToBottom(0, 0, triangle.size(), triangle);

        return minimumTotalBottomToTop(triangle);
    }
    /**
     * 暴力递归
     *
     * @param i
     * @param j
     * @param n
     * @param triangle
     * @return int
     */
    private int minimumTotalRecursively(int i, int j, int n, List<List<Integer>> triangle) {
        if (i >= n) {
            return 0;
        }
        return triangle.get(i).get(j) +
        Math.min(
            minimumTotalRecursively(i + 1, j, n, triangle),
            minimumTotalRecursively(i + 1, j + 1, n, triangle)
        );
    }
    /**
     * 记忆化搜索
     *
     * @param i
     * @param j
     * @param n
     * @param triangle
     * @return int
     */
    private int[][] memo;
    private int minimumTotalTopToBottom(int i, int j, int n, List<List<Integer>> triangle) {
        if (i >= n) {
            return 0;
        }
        if (memo[i][j] == -1) {
            memo[i][j] = triangle.get(i).get(j) +
            Math.min(
                minimumTotalTopToBottom(i + 1, j, n, triangle),
                minimumTotalTopToBottom(i + 1, j + 1, n, triangle)
            );
        }
        return memo[i][j];
    }
    /**
     * 动态递推
     *
     * @param triangle
     * @return int
     */
    private int minimumTotalBottomToTop(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size() + 1];
        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[j] = triangle.get(i).get(j)
                + Math.min(dp[j], dp[j + 1]);
            }
        }
        return dp[0];
    }
}
/* EOF */