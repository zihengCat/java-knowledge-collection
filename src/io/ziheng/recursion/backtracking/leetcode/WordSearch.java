package io.ziheng.recursion.backtracking.leetcode;

/**
 * LeetCode 79. Word Search
 * https://leetcode.com/problems/word-search/
 */
public class WordSearch {
    private int colNum;
    private int rowNum;
    private boolean[][] visited;
    private int[][] distances = {
        { 1,  0, },
        {-1,  0, },
        { 0,  1, },
        { 0, -1, },
    };
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0
            || board[0] == null || board[0].length == 0
            || word == null || word.length() == 0) {
            return false;
        }
        rowNum = board.length;
        colNum = board[0].length;
        visited = new boolean[rowNum][colNum];
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (board[i][j] == word.charAt(0) && dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean dfs(char[][] board, String word, int i, int j, int index) {
        if (index == word.length()) {
            return true;
        }
        if (!inArea(i, j)
            || visited[i][j]
            || board[i][j] != word.charAt(index)) {
            return false;
        }
        visited[i][j] = true;
        for (int k = 0; k < distances.length; k++) {
            int nextX = i + distances[k][0];
            int nextY = j + distances[k][1];
            if (dfs(board, word, nextX, nextY, index + 1)) {
                return true;
            }
        }
        visited[i][j] = false;
        return false;
    }
    private boolean inArea(int x, int y) {
        return x >= 0 && x < rowNum && y >= 0 && y < colNum;
    }
}
/* EOF */