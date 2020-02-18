package io.ziheng.graph.leetcode;
/**
 * LeetCode 695. Max Area of Island
 * https://leetcode.com/problems/max-area-of-island/
 */
public class MaxAreaOfIsland {
    public static void main(String[] args) {
        int[][] grid = {
            {0,0,1,0,0,0,0,1,0,0,0,0,0},
            {0,0,0,0,0,0,0,1,1,1,0,0,0},
            {0,1,1,0,1,0,0,0,0,0,0,0,0},
            {0,1,0,0,1,1,0,0,1,0,1,0,0},
            {0,1,0,0,1,1,0,0,1,1,1,0,0},
            {0,0,0,0,0,0,0,0,0,0,1,0,0},
            {0,0,0,0,0,0,0,1,1,1,0,0,0},
            {0,0,0,0,0,0,0,1,1,0,0,0,0},
        };
        MaxAreaOfIsland maxAreaOfIsland = new MaxAreaOfIsland();
        System.out.println(maxAreaOfIsland.maxAreaOfIsland(grid));
    }
    private static int[][] directions = {
        {-1,  0, },
        { 1,  0, },
        { 0, -1, },
        { 0,  1, },
    };
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null ||grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rowNum = grid.length;
        int colNum = grid[0].length;
        boolean[][] visited = new boolean[rowNum][colNum];
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                visited[i][j] = false;
            }
        }
        int result = 0;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    result = Math.max(
                        result,
                        depthFirstSearch(i, j, visited, grid)
                    );
                }
            }
        }
        return result;
    }
    private int depthFirstSearch(
        int x, int y, boolean[][] visited, int[][] grid) {
        visited[x][y] = true;
        int result = 1;
        for (int i = 0; i < directions.length; i++) {
            int nextX = x + directions[i][0];
            int nextY = y + directions[i][1];
            if (isInArea(nextX, nextY, grid) &&
                !visited[nextX][nextY] &&
                grid[nextX][nextY] == 1) {
                result += depthFirstSearch(nextX, nextY, visited, grid);
            }
        }
        return result;
    }
    private boolean isInArea(int x, int y, int[][] grid) {
        return x >= 0 && x < grid.length &&
               y >= 0 && y < grid[0].length;
    }
}
/* EOF */