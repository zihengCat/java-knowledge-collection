package io.ziheng.search.leetcode;

/**
 * LeetCode 733. Flood Fill
 * https://leetcode.com/problems/flood-fill/
 */
public class FloodFill {
    private static int[][] directions = {
        { 1, 0},
        {-1, 0},
        {0, 1},
        {0, -1},
    };
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0) {
            return image;
        }
        boolean[][] visited = new boolean[image.length][image[0].length];
        floodFillDFS(image, sr, sc, image[sr][sc], newColor, visited);
        return image;
    }
    private void floodFillDFS(
        int[][] image,
        int currentRow,
        int currentCol,
        int originalColor,
        int newColor,
        boolean[][] visited) {
        if (!isValidPosition(image, currentRow, currentCol)
            || visited[currentRow][currentCol]
            || image[currentRow][currentCol] != originalColor) {
            return;
        }
        visited[currentRow][currentCol] = true;
        image[currentRow][currentCol] = newColor;
        for (int[] direction : directions) {
            floodFillDFS(
                image,
                currentRow + direction[0],
                currentCol + direction[1],
                originalColor,
                newColor,
                visited
            );
        }
    }
    private boolean isValidPosition(int[][] arr, int rowNum, int colNum) {
        return rowNum >= 0 && rowNum < arr.length
            && colNum >= 0 && colNum < arr[rowNum].length;
    }
}
/* EOF */