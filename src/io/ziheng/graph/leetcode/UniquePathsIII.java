package io.ziheng.graph.leetcode;
/**
 * LeetCode 980. Unique Paths III
 * https://leetcode.com/problems/unique-paths-iii/
 */
public class UniquePathsIII {
    public static void main(String[] args) {
        int[][] grid = new int[][] {
            {1, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 2,-1},
        };
        System.out.println(
            new UniquePathsIII().uniquePathsIII(grid)
        );
    }
    private static final int STARTING_SQUARE = 1;
    private static final int ENDING_SQUARE = 2;
    private static final int EMPTY_SQUARE = 0;
    private static final int OBSTACLES_SQUARE = -1;
    private static int[][] directions = {
        { 1,  0, },
        {-1,  0, },
        { 0,  1, },
        { 0, -1, },
    };
    private int rowNum;
    private int colNum;
    private int startVertex;
    private int endVertex;
   /**
    * [
    *   [1, 0, 0, 0],
    *   [0, 0, 0, 0],
    *   [0, 0, 2,-1],
    * ]
    */
    public int uniquePathsIII(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        rowNum = grid.length;
        colNum = grid[0].length;
        boolean[][] visited = new boolean[rowNum][colNum];
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                visited[i][j] = false;
            }
        }
        int unVisitedVerticesNum = rowNum * colNum;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (grid[i][j] == STARTING_SQUARE) {
                    startVertex = i * colNum + j;
                    grid[i][j] = EMPTY_SQUARE;
                } else if (grid[i][j] == ENDING_SQUARE) {
                    endVertex = i * colNum + j;
                    grid[i][j] = EMPTY_SQUARE;
                } else if (grid[i][j] == OBSTACLES_SQUARE) {
                    unVisitedVerticesNum--;
                }
            }
        }
        return depthFirstSearch(
            startVertex,
            unVisitedVerticesNum,
            visited,
            grid
        );
    }
    private int depthFirstSearch(
        int vertex, int unVisitedVerticesNum,
        boolean[][] visited, int[][] grid) {
        int vertexX = vertex / colNum;
        int vertexY = vertex % colNum;
        visited[vertexX][vertexY] = true;
        unVisitedVerticesNum--;
        if (vertex == endVertex && unVisitedVerticesNum == 0) {
            // 回溯状态
            visited[vertexX][vertexY] = false;
            return 1;
        }
        int result = 0;
        for (int i = 0; i < directions.length; i++) {
            int nextX = vertexX + directions[i][0];
            int nextY = vertexY + directions[i][1];
            if (inArea(nextX, nextY) &&
                grid[nextX][nextY] == EMPTY_SQUARE &&
                !visited[nextX][nextY]) {
                result += depthFirstSearch(
                    nextX * colNum + nextY,
                    unVisitedVerticesNum, visited, grid
                );
            }
        }
        visited[vertexX][vertexY] = false;
        //unVisitedVerticesNum++;
        return result;
    }
    private boolean inArea(int x, int y) {
        return x >= 0 && x < rowNum &&
               y >= 0 && y < colNum;
    }
}
/* EOF */