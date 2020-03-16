package io.ziheng.recursion.dynamicprogramming.leetcode;

import java.util.Arrays;

/**
 * LeetCode 63. Unique Paths II
 * https://leetcode.com/problems/unique-paths-ii/
 */
public class UniquePathsII {
    public static void main(String[] args) {
        UniquePathsII obj = new UniquePathsII();
        int[][] grid = {
            {0,0,0},
            {0,1,0},
            {0,0,0},
        };
        System.out.println(
            obj.uniquePathsWithObstacles(grid)
        );
    }
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        } 
        int rowNum = obstacleGrid.length;
        int colNum = obstacleGrid[0].length;
        // boolean[][] visited = new boolean[rowNum][colNum];
        memo = new int[rowNum + 1][colNum + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(0, 0, rowNum - 1, colNum - 1, obstacleGrid);
    }
    private int[][] memo;
    /**
     * 记忆化搜索
     *
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @param obstacleGrid
     * @return int
     */
    private int dfs(
        int startX, int startY,
        int endX, int endY,
        int[][] obstacleGrid) {
        if (!isVaild(startX, startY, obstacleGrid)) {
            return 0;
        }
        if (memo[startX][startY] == -1) {
            if (startX == endX && startY == endY) {
                memo[startX][startY] = 1;
            } else {
                memo[startX][startY] =
                dfs(startX, startY + 1, endX, endY, obstacleGrid)
                + dfs(startX + 1, startY, endX, endY, obstacleGrid);
            }
        }
        return memo[startX][startY];
    }
    private boolean isVaild(int i, int j, int[][] obstacleGrid) {
        return i >= 0 && i < obstacleGrid.length
            && j >= 0 && j < obstacleGrid[i].length
            && obstacleGrid[i][j] != 1;
    }
}
/* EOF */