package io.ziheng.array.leetcode;

import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * LeetCode 73. Set Matrix Zeroes
 * https://leetcode.com/problems/set-matrix-zeroes/
 */
public class SetMatrixZeroes {
    /**
     * 主函数 -> 测试用例
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        SetMatrixZeroes obj = new SetMatrixZeroes();
        int[][] matrix = {
            {0,1,2,0,},
            {3,4,5,2,},
            {1,3,1,5,},
        };
        obj.setZeroes(matrix);
        System.out.println(
            Arrays.deepToString(matrix)
        );
    }
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        List<Integer[]> list = new LinkedList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    list.add(new Integer[]{i, j});
                }
            }
        }
        for (Integer[] item : list) {
            setZeroes(item[0], item[1], matrix);
        }
    }
    /**
     * [
     *      [0,1,2,0],
     *      [3,4,5,2],
     *      [1,3,1,5],
     * ]
     * ->
     * [
     *      [0,0,0,0],
     *      [0,4,5,0],
     *      [0,3,1,0],
     * ]
     */
    private void setZeroes(int rowNum, int colNum, int[][] matrix) {
        for (int i = 0; i < matrix[rowNum].length; i++) {
            matrix[rowNum][i] = 0;
        }
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][colNum] = 0;
        }
    }
}
/* EOF */