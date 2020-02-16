package io.ziheng.graph.leetcode;
import java.util.*;
public class Solution {
    public static void main(String[] args) {
        int[][] board = new int[][] {
            {1, 2, 3, 4, 5,  },
            {6+5, 7+5, 8+5, 9+5,  0, } ,
            //{7, 8, 9, 10, 11,} ,
        };
        int i = new Solution().slidingPuzzle(board);
        System.out.println(i);
    }
    public int slidingPuzzle(int[][] board) {
        // m * n version
        int m = board.length;
        int n = board[0].length;
        //build target string
        //1->A 2->B ...etc
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m * n - 1; i++){
            sb.append((char)(i+65));
        }
        sb.append("0");
        String target = sb.toString();
        
        //build start string
        String start = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0){
                    start += 0;
                }else {
                    start += (char)(board[i][j] +64);
                }
            }
        }
        
        HashSet<String> visited = new HashSet<>();
        int[][] dirs = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }};
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start);
        int res = 0;
        //BFS
        int cnt = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (cur.equals(target)) {
                    return res;
                }
                int index = cur.indexOf('0');
                int x = index / n;
                int y = index % n;
                for (int[] dir : dirs){
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    if (nx >= 0 && nx <m && ny >= 0 && ny < n){
                        int indexToSwap = nx * n + ny; 
                        String next = swap(cur, index, indexToSwap);
                        if (visited.contains(next)){
                            continue;
                        }
                        visited.add(next);
                        queue.offer(next);
                        cnt++;
                    }
                }
            }
            res++;
        }
        System.out.println(cnt);
        return -1;
    }
    private String swap(String str, int i, int j) {
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(i, str.charAt(j));
        sb.setCharAt(j, str.charAt(i));
        return sb.toString();
    }
}
