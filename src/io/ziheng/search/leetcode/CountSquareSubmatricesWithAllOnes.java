package io.ziheng.search.leetcode;

import java.util.Arrays;

/**
 * LeetCode 1277. Count Square Submatrices with All Ones
 * https://leetcode.com/problems/count-square-submatrices-with-all-ones/
 */
public class CountSquareSubmatricesWithAllOnes {
    public int countSquares(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int cnt = 0;
        int[][] visited = new int[matrix.length][matrix[0].length];
        for (int[] row : visited) {
            Arrays.fill(row, -1);
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    cnt += countSquaresDFS(matrix, i, j, visited);
                }
            }
        }
        return cnt;
    }
    private int countSquaresDFS(int[][] matrix, int i, int j, int[][] visited) {
        if (i < 0 || i >= matrix.length
            || j < 0 || j >= matrix[i].length
            || matrix[i][j] != 1) {
            return 0;
        }
        if (visited[i][j] != -1) {
            return visited[i][j];
        }
        int cnt = 0;
        int cntBottom = countSquaresDFS(matrix, i, j + 1, visited);
        int cntRight = countSquaresDFS(matrix, i + 1, j, visited);
        int cntBottomRight = countSquaresDFS(matrix, i + 1, j + 1, visited);
        cnt = 1 + Math.min(cntBottomRight, Math.min(cntBottom, cntRight));
        visited[i][j] = cnt;
        return cnt;
    }
}
/* EOF */