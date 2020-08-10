package io.ziheng.search.leetcode;

import java.util.Queue;
import java.util.LinkedList;

/**
 * LeetCode 994. Rotting Oranges
 * https://leetcode.com/problems/rotting-oranges/
 */
public class RottingOranges {
    public static void main(String[] args) {
        // ...
    }
    private int[][] directions = new int[][]{
        { 1,  0,},
        {-1,  0,},
        { 0,  1,},
        { 0, -1,},
    };
    private static int EMPTY = 0;
    private static int FRESH = 1;
    private static int ROTTEN = 2;
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return -1;
        }
        Queue<int[]> aQueue = new LinkedList<>();
        int freshCount = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == FRESH) {
                    freshCount++;
                } else if (grid[i][j] == ROTTEN) {
                    aQueue.offer(new int[]{i, j});
                }
            }
        }
        int timeCount = 0;
        while (!aQueue.isEmpty() && freshCount > 0) {
            int size = aQueue.size();
            for (int i = 0; i < size; i++) {
                int[] pos = aQueue.poll();
                for (int[] d : directions) {
                    int x = pos[0] + d[0];
                    int y = pos[1] + d[1];
                    if (!isValidPosition(grid, x, y)
                        || grid[x][y] != FRESH) {
                        continue;
                    }
                    grid[x][y] = ROTTEN;
                    freshCount--;
                    aQueue.offer(new int[]{x, y});
                }
            }
            timeCount++;
        }
        return freshCount == 0 ? timeCount : -1;
    }
    private boolean isValidPosition(int[][] grid, int i, int j) {
        return i >= 0 && i < grid.length
            && j >= 0 && j < grid[i].length;
    }
}
/* EOF */