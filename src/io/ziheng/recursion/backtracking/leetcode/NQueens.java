package io.ziheng.recursion.backtracking.leetcode;

import java.util.List;
import java.util.LinkedList;

/**
 * LeetCode 51. N-Queens
 * https://leetcode.com/problems/n-queens/
 */
public class NQueens {
    public static void main(String[] args) {
        System.out.println(
            new NQueens().solveNQueens(4)
        );
    }
    /**
     * . -> 可用
     * Q -> 皇后
     */
    char[][] chessBoard;
    List<List<String>> resultList = new LinkedList<>();
    public List<List<String>> solveNQueens(int n) {
        chessBoard = buildChessBoard(n);
        solveNQueens0(0, n, chessBoard);
        return resultList;
    }
    private void solveNQueens0(int colIndex, int n, char[][] currentChessBoard) {
        if (colIndex >= n) {
            putResult(currentChessBoard);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (isVaild(i, colIndex, n, currentChessBoard)) {
                // 放置皇后
                currentChessBoard[i][colIndex] = 'Q';
                // 递归向下
                solveNQueens0(colIndex + 1, n, currentChessBoard);
                // 恢复状态
                currentChessBoard[i][colIndex] = '.';
            }
        }
    }
    private boolean isVaild(int rowIndex, int colIndex, int n, char[][] chessBoard) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < colIndex; j++) {
                if (chessBoard[i][j] == 'Q'
                    && (rowIndex == i // 竖直方向
                        || colIndex == j // 水平方向
                        || rowIndex + j == colIndex + i // 右斜方向
                        || rowIndex + colIndex == i + j // 左斜方向
                    )) {
                    return false;
                }
            }
        }
        return true;
    }
    private void putResult(char[][] chessBoard) {
        List<String> res = new LinkedList<String>();
        for(int i = 0; i < chessBoard.length; i++) {
            String s = String.valueOf(chessBoard[i]);
            res.add(s);
        }
        resultList.add(res);
    }
    private char[][] buildChessBoard(int n) {
        char[][] chessBoard = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chessBoard[i][j] = '.';
            }
        }
        return chessBoard;
    }
}
/* EOF */