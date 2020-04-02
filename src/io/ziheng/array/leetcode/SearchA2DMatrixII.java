package io.ziheng.array.leetcode;
/**
 * LeetCode 240. Search a 2D Matrix II
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 */
public class SearchA2DMatrixII {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int rowNum = 0;
        int colNum = matrix[rowNum].length - 1;
        while (rowNum >= 0 && rowNum < matrix.length
            && colNum >= 0 && colNum < matrix[rowNum].length) {
            int num = matrix[rowNum][colNum];
            if (target > num) {
                rowNum++;
            } else if (target < num) {
                colNum--;
            } else {
                return true;
            }
        }
        return false;
    }
}
/* EOF */