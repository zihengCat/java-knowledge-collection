package io.ziheng.search.leetcode;
/**
 * LeetCode 74. Search a 2D Matrix
 * https://leetcode.com/problems/search-a-2d-matrix/
 */
public class SearchA2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        // return searchMatrixBruteForce(matrix, target);
        return searchMatrixBinarySearch(matrix, target);
    }
    private boolean searchMatrixBinarySearch(int[][] matrix, int target) {
        for (int i = 0, j = matrix[i].length - 1;
            i >= 0 && i < matrix.length
            && j >= 0 && j < matrix[i].length;
            /* ... */ ) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                j--;
            } else if (matrix[i][j] < target) {
                i++;
            }
        }
        return false;
    }
    private boolean searchMatrixBruteForce(int[][] matrix, int target) {
        for (int[] row : matrix) {
            for (int num : row) {
                if (num == target) {
                    return true;
                }
            }
        }
        return false;
    }
}
/* EOF */