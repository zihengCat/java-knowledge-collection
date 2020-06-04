package io.ziheng.codinginterviews;

import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 剑指 Offer 面试题 29：顺时针打印矩阵
 *
 * 题目描述：
 * 输入一个矩阵，按照从外向里以顺时针顺序依次打印该矩阵。
 *
 * 举例说明：
 * 输入：
 * [[ 1,  2,  3,  4],
 *  [ 5,  6,  7,  8],
 *  [ 9, 10, 11, 12],
 *  [13, 14, 15, 16]]
 *
 * 输出：[1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10]
 *
 * 知识点：["数组"]
 */
public class ClockwisePrintMatrix {
    /**
     * 主函数 -> 测试用例
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        ClockwisePrintMatrix obj = new ClockwisePrintMatrix();
        int[][] matrix = buildMatrix(4);
        System.out.println(Arrays.deepToString(matrix));
        System.out.println(
            obj.clockwisePrintMatrix(matrix)
        );
    }
    /**
     * 剑指 Offer 面试题 29：顺时针打印矩阵
     *
     * 时间复杂度：O(n * m)
     * 空间复杂度：O(1)
     *
     * @param matrix
     * @return {@code List<List<Integer>>}
     */
    public List<List<Integer>> clockwisePrintMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new LinkedList<>();
        }
        List<List<Integer>> resultList = new LinkedList<>();
        int startX = 0;
        int startY = 0;
        int rowNum = matrix.length;
        int colNum = matrix[0].length;
        while (rowNum > startX * 2 && colNum > startY * 2) {
            List<Integer> aList = new LinkedList<>();
            printMatrixCicle(matrix, startX, startY, aList);
            resultList.add(aList);
            startX++;
            startY++;
        }
        return resultList;
    }
    private void printMatrixCicle(
        int[][] matrix, int startX, int startY, List<Integer> aList) {
        int endX = matrix.length - 1 - startX;
        int endY = matrix[0].length - 1 - startY;
        // First Line: left -> right
        for (int row = startX, col = startY; col <= endY; col++) {
            aList.add(matrix[row][col]);
        }
        // Second Line: right -> bottom
        for (int row = startX + 1, col = endY; row <= endX; row++) {
            aList.add(matrix[row][col]);
        }
        // Thrid Line: bottom right -> bottom left
        for (int row = endX, col = endY - 1; col >= startY; col--) {
            aList.add(matrix[row][col]);
        }
        // Fourth Line: bottom -> top
        for (int row = endX - 1, col = startY; row > startX; row--) {
            aList.add(matrix[row][col]);
        }
    }
    private static int[][] buildMatrix(int n) {
        int[][] matrix = new int[n][n];
        int num = 1;
        for (int[] row : matrix) {
            for (int i = 0; i < row.length; i++) {
                row[i] = num;
                num++;
            }
        }
        return matrix;
    }
}
/* EOF */