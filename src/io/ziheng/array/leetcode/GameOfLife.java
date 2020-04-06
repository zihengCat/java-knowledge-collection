package io.ziheng.array.leetcode;
/**
 * LeetCode 289. Game of Life
 * https://leetcode.com/problems/game-of-life/
 */
public class GameOfLife {
    private int[][] directions = new int[][] {
        { 0,  1, },
        { 0, -1, },
        { 1,  0, },
        {-1,  0, },
        { 1,  1, },
        {-1, -1, },
        { 1, -1, },
        {-1,  1, },
    };
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        int[][] newBoard = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int cellState = board[i][j];
                int cellsAround = findLiveCellsAround(i, j, board);
                if (cellState == 1
                    && cellsAround < 2) {
                    newBoard[i][j] = 0;
                } else if (cellState == 1
                    && cellsAround >= 2
                    && cellsAround <= 3) {
                    newBoard[i][j] = 1;
                } else if (cellState == 1
                    && cellsAround > 3) {
                    newBoard[i][j] = 0;
                } else if (cellState == 0
                    && cellsAround == 3) {
                    newBoard[i][j] = 1;
                } else {
                    newBoard[i][j] = 0;
                }
            }
        }
        fillBoard(newBoard, board);
    }
    private void fillBoard(int[][] newBoard, int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = newBoard[i][j];
            }
        }
    }
    // private int[][] copyBoard(int[][] board) {
    //     int[][] newBoard = new int[board.length][board[0].length];
    //     for (int i = 0; i < board.length; i++) {
    //         for (int j = 0; j < board[i].length; j++) {
    //             newBoard[i][j] = board[i][j];
    //         }
    //     }
    //     return newBoard;
    // }
    private int findLiveCellsAround(int x, int y, int[][] board) {
        int cnt = 0;
        for (int[] direction : directions) {
            int nextX = x + direction[0];
            int nextY = y + direction[1];
            if (isVaild(nextX, nextY, board)
                && board[nextX][nextY] == 1) {
                cnt++;
            }
        }
        return cnt;
    }
    private boolean isVaild(int x, int y, int[][] board) {
        return x >= 0 && x < board.length
            && y >= 0 && y < board[x].length;
    }
}
/* EOF */