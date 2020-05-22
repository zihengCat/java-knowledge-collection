package io.ziheng.codinginterviews;

import java.util.Arrays;

/**
 * 剑指 Offer 面试题 47：礼物的最大价值
 *
 * 题目描述：
 * 在一个 n * m 棋盘的每一格都放有一个礼物，
 * 每个礼物都有一定价值，礼物价值大于零。从棋盘左上角
 * 开始拿格子里的礼物，每次向右或者向下移动一格，直到
 * 到达棋盘的右下角，求所能拿到礼物的最大价值。
 *
 * 样例：
 * 输入：
 *
 * 1 10  3  8
 * 12 2  9  6
 * 5  7  4 11
 * 3  7 16  5
 *
 * 输出：53（路径：1 -> 12 -> 5 -> 7 -> 7 -> 16 -> 5）
 *
 * 知识点：["深度优先搜索", "动态规划"]
 */
public class MaximumValueOfGifts {
    /**
     * 主函数 -> 测试用例
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        MaximumValueOfGifts obj = new MaximumValueOfGifts();
        int[][] grid = new int[][] {
            {1, 10,  3,  8,},
            {12, 2,  9,  6,},
            {5,  7,  4, 11,},
            {3,  7, 16,  5,},
        };
        System.out.println(
            obj.maximumValueOfGifts(grid)
        );
    }
    /**
     * 剑指 Offer 面试题 47：礼物的最大价值 -> 记忆化搜索
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param grid
     * @return int
     */
    public int maximumValueOfGifts(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int[][] memo = new int[grid.length][grid[0].length];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return maximumValueOfGiftsDFS(grid, 0, 0, memo);
    }
    private int maximumValueOfGiftsDFS(
        int[][] grid, int i, int j, int[][] memo) {
        if (i < 0 || i >= grid.length
            || j < 0 || j >= grid[i].length) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int rightValue = grid[i][j] + maximumValueOfGiftsDFS(grid, i + 1, j, memo);
        int bottomValue = grid[i][j] + maximumValueOfGiftsDFS(grid, i, j + 1, memo);
        memo[i][j] = Math.max(rightValue, bottomValue);
        return memo[i][j];
    }
}
/* EOF */