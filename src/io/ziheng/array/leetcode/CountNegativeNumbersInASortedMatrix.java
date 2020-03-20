package io.ziheng.array.leetcode;
/**
 * LeetCode 1351. Count Negative Numbers in a Sorted Matrix
 * https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/
 */
public class CountNegativeNumbersInASortedMatrix {
    private int cnt = 0;
    public int countNegatives(int[][] grid) {
        if (grid == null) {
            return 0;
        }
        countNegatives0(0, 0, grid);
        return cnt;
    }
    private void countNegatives0(int x, int y, int[][] grid) {
        /**
         * [
         *     [4,3,2,-1],
         *     [3,2,1,-1],
         *     [1,1,-1,-2],
         *     [-1,-1,-2,-3]
         * ]
         */
        if (x >= grid.length || y >= grid[0].length) {
            return;
        }
        for (int i = x; i < grid[0].length; i++) {
            if (grid[x][i] < 0) {
                cnt++;
            }
        }
        for (int i = x + 1; i < grid.length; i++) {
            if (grid[i][y] < 0) {
                cnt++;
            }
        }
        countNegatives0(x + 1, y + 1, grid);
    }
}
/* EOF */