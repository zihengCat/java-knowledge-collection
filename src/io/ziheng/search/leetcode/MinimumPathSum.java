package io.ziheng.search.leetcode;

import java.util.Arrays;

/**
 * LeetCode 64. Minimum Path Sum
 * https://leetcode.com/problems/minimum-path-sum/
 */
public class MinimumPathSum {

    public static void main(String[] args) {
        MinimumPathSum obj = new MinimumPathSum();
        int[][] grid = {
           {1,3,1,},
           {1,5,1,},
           {4,2,1,},
        };
        System.out.println(
            obj.minPathSum(grid)
        );
    }

    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        // 暴力递归
        // pathSumBruteForceRecursion(0, 0, 0, grid);
        // return minPathSum;

        // 记忆化搜索
        int[][] memo = new int[grid.length + 1][grid[0].length + 1];
        for (int[] row : memo) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        return pathSumTopToBottom(0, 0, grid, memo);
    }

    /**
     * 自顶向下 -> 记忆化搜索
     *
     * @param i
     * @param j
     * @param grid
     * @param memo
     * @return int
     */
    private int pathSumTopToBottom(int i, int j, int[][] grid, int[][] memo) {
        if (i < 0 || i >= grid.length
            || j < 0 || j >= grid[i].length) {
            return Integer.MAX_VALUE;
        } else if (i == grid.length - 1 && j == grid[i].length - 1) {
            return grid[i][j];
        }
        if (memo[i][j] != Integer.MAX_VALUE) {
            return memo[i][j];
        }
        int moveDown = pathSumTopToBottom(i + 1, j, grid, memo);
        int moveRight = pathSumTopToBottom(i, j + 1, grid, memo);
        memo[i][j] = grid[i][j] + Math.min(moveDown, moveRight);
        return memo[i][j];
    }

    private int minPathSum = Integer.MAX_VALUE;
    private void pathSumBruteForceRecursion(int i, int j, int currentSum, int[][] grid) {
        if (i < 0 || i >= grid.length
            || j < 0 || j >= grid[i].length) {
            return;
        }
        if (i == grid.length - 1 && j == grid[grid.length - 1].length - 1) {
            minPathSum = Math.min(
                minPathSum,
                currentSum + grid[i][j]
            );
        }
        // Move Down
        pathSumBruteForceRecursion(i + 1, j, currentSum + grid[i][j], grid);
        // Move Right
        pathSumBruteForceRecursion(i, j + 1, currentSum + grid[i][j], grid);
    }
}
/* EOF */