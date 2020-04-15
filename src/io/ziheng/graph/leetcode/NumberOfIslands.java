package io.ziheng.graph.leetcode;

/**
 * LeetCode 200. Number of Islands
 * https://leetcode.com/problems/number-of-islands/
 */
public class NumberOfIslands {
    public static void main(String[] args) {
        NumberOfIslands obj = new NumberOfIslands();
        char[][] grid = new char[][]{
            {'1', '1', '0', '0', '0', },
            {'1', '1', '0', '0', '0', },
            {'0', '0', '1', '0', '0', },
            {'0', '0', '0', '1', '1', },
        };
        System.out.println(
            obj.numIslands(grid)
        );
    }
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int cnt = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    dfsFill(i, j, grid);
                    cnt++;
                }
            }
        }
        return cnt;
    }
    private void dfsFill(int i, int j, char[][] grid) {
        if (i < 0 || i >= grid.length
        || j < 0 || j >= grid[i].length
        || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '0';
        dfsFill(i + 1, j, grid);
        dfsFill(i - 1, j, grid);
        dfsFill(i, j + 1, grid);
        dfsFill(i, j - 1, grid);
    }
}
/* EOF */