package io.ziheng.array.leetcode;

import java.util.List;
import java.util.LinkedList;

/**
 * LeetCode 1380. Lucky Numbers in a Matrix
 * https://leetcode.com/problems/lucky-numbers-in-a-matrix/
 */
public class LuckyNumbersInAMatrix {
    public List<Integer> luckyNumbers (int[][] matrix) {
        if (matrix == null) {
            return new LinkedList<>();
        }
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (isLuckyNumber(i, j, matrix)) {
                    list.add(matrix[i][j]);
                }
            }
        }
        return list;
    }
    private boolean isLuckyNumber(int x, int y, int[][] matrix) {
        int target = matrix[x][y];
        for (int i = 0; i < matrix[x].length; i++) {
            if (matrix[x][i] < target) {
                return false;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][y] > target) {
                return false;
            }
        }
        return true;
    }
}
/* EOF */