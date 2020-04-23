package io.ziheng.array.leetcode;
/**
 * LeetCode 832. Flipping an Image
 * https://leetcode.com/problems/flipping-an-image/
 */
public class FlippingAnImage {
    public int[][] flipAndInvertImage(int[][] matrix) {
        flipImage(matrix);
        invertImage(matrix);
        return matrix;
    }
    private void invertImage(int[][] matrix) {
        for (int[] row : matrix) {
            for (int i = 0; i < row.length; i++) {
                row[i] = row[i] == 0 ? 1 : 0;
            }
        }
    }
    private void flipImage(int[][] matrix) {
        for (int[] row : matrix) {
            for (int pLeft = 0, pRight = row.length - 1;
                pLeft < pRight;
                pLeft++, pRight--) {
                swap(row, pLeft, pRight);
            }
        }
    }
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
/* EOF */