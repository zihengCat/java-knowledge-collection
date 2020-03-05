package io.ziheng.recursion.dynamicprogramming.leetcode;
import java.util.List;

public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int[][] memory = new int[triangle.size()][triangle.size()];
        for (int i = 0; i < memory.length; i++) {
            for (int j = 0; j < memory.length; j++) {
                memory[i][j] = Integer.MAX_VALUE;
            }
        }
        return minimumTotal0(triangle, triangle.size(), 0, 0, memory);
    }
    private int minimumTotal0(
        List<List<Integer>> triangle,
        int n,
        int i, int j,
        int[][] memory) {
        if (i == n - 1) {
            return triangle.get(0).get(0);
        }
        if (memory[i][j] != Integer.MAX_VALUE) {
            return memory[i][j];
        }
        memory[i][j] = triangle.get(i).get(j) + Math.min(
            minimumTotal0(triangle, n, i + 1, j, memory),
            minimumTotal0(triangle, n, i + 1, j + 1, memory)
        );
        return memory[i][j];
    }
}