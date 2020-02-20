package io.ziheng.graph.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * LeetCode 773. Sliding Puzzle
 * https://leetcode.com/problems/sliding-puzzle/
 */
public class SlidingPuzzle {
    private int[][] directions = {
        {-1,  0, },
        { 1,  0, },
        { 0,  1, },
        { 0, -1, },
    };
    private int rowNum;
    private int colNum;
    public int slidingPuzzle(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return -1;
        }
        rowNum = board.length;
        colNum = board[0].length;
        String initialState = boardToString(board);
        /**
         * [[1, 2, 3,],
         *  [4, 5, 0,]]
         */
        String finalState = "123450";
        // 如果初始盘面已为最终盘面 -> 提前返回
        if (initialState.equals(finalState)) {
            return 0;
        }
        // BFS
        Queue<String> queue = new LinkedList<>();
        Map<String, Integer> visited = new HashMap<>();
        queue.offer(initialState);
        visited.put(initialState, 0);
        while (!queue.isEmpty()) {
            String currentState = queue.poll();
            List<String> nextStates = getNextStates(currentState);
            for (String adjState : nextStates) {
                if (!visited.containsKey(adjState)) {
                    queue.offer(adjState);
                    visited.put(adjState, visited.get(currentState) + 1);
                    if (adjState.equals(finalState)) {
                        return visited.get(adjState);
                    }
                }
            }
        }
        return -1;
    }
    private List<String> getNextStates(String currentBoardState) {
        List<String> resultList = new ArrayList<String>();
        int[][] currentBoard = stringToBoard(currentBoardState);
        //int zeroIndex = -1;
        int zeroX = -1;
        int zeroY = -1;
        for (int i = 0; i < currentBoard.length; i++) {
            for (int j = 0; j < currentBoard[i].length; j++) {
                if (currentBoard[i][j] == 0) {
                    zeroX = j;
                    zeroY = i;
                    //zeroIndex = i * rowNum + j;
                }
            }
        }
        for (int i = 0; i < directions.length; i++) {
            int nextX = zeroX + directions[i][0];
            int nextY = zeroY + directions[i][1];
            if (inBoard(nextX, nextY)) {
                swap(currentBoard, zeroX, zeroY, nextX, nextY);
                resultList.add(boardToString(currentBoard));
                // 恢复盘面
                swap(currentBoard, zeroX, zeroY, nextX, nextY);
            }
        }
        return resultList;
    }
    private void swap(
        int[][] currentBoard,
        int xA, int yA,
        int xB, int yB) {
        int tmp = currentBoard[yA][xA];
        currentBoard[yA][xA] = currentBoard[yB][xB];
        currentBoard[yB][xB] = tmp;
    }
    private boolean inBoard(int x, int y) {
        return x >= 0 && x < colNum &&
               y >= 0 && y < rowNum;
    }
    private String boardToString(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }
    private int[][] stringToBoard(String boardState) {
        int[][] board = new int[rowNum][colNum];
        for (int i = 0; i < boardState.length(); i++) {
            board[i / colNum][i % colNum] = charToInt(boardState.charAt(i));
        }
        return board;
    }
    private int charToInt(char c) {
        return c - '0';
    }
}
/* EOF */