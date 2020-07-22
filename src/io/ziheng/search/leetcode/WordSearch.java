package io.ziheng.search.leetcode;
/**
 * LeetCode 79. Word Search
 * https://leetcode.com/problems/word-search/
 */
public class WordSearch {
    public static void main(String[] args) {
        WordSearch obj = new WordSearch();
        char[][] board = new char[][] {
            {'A','B','C','E', },
            {'S','F','C','S', },
            {'A','D','E','E', },
        };
        String word = "ABCCED";
        System.out.println(obj.exist(board, word));
    }
    private int[][] distances = new int[][] {
        { 1,  0, },
        {-1,  0, },
        { 0,  1, },
        { 0, -1, },
    };
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0
            || word == null || word.length() == 0) {
            return false;
        }
        char[] wordCharArr = word.toCharArray();
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == wordCharArr[0]
                    && dfs(board, i, j, wordCharArr, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean dfs(char[][] board, int x, int y, char[] word, int idx, boolean[][] visited) {
        if (idx == word.length) {
            return true;
        }
        if (!inBoardArea(board, x, y)
            || visited[x][y]
            || board[x][y] != word[idx]) {
            return false;
        }
        visited[x][y] = true;
        for (int[] row : this.distances) {
            if (dfs(board, x + row[0], y + row[1], word, idx + 1, visited)) {
                return true;
            }
        }
        visited[x][y] = false;
        return false;
    }
    private boolean inBoardArea(char[][] board, int i, int j) {
        return i >= 0 && i < board.length
            && j >= 0 && j < board[i].length;
    }
}
/* EOF */