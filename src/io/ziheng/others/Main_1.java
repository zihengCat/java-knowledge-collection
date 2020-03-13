// package io.ziheng.others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 数据读入
        Scanner scanner = new Scanner(System.in);
        // 2 * n 格子
        int n = 0;
        char[][] grid = null;
        for (int i = 0; i < 3; i++) {
            String line = scanner.nextLine();
            if (i == 0) {
                n = Integer.parseInt(line);
                grid = new char[2][n];
            } else {
                for (int j = 0; j < n; j++) {
                    grid[i - 1][j] = line.charAt(j);
                }
            }
        }
        // System.out.println(n);
        // System.out.println(Arrays.deepToString(grid));
        scanner.close();

        // 计算结果
        Main mainClass = new Main();
        mainClass.rowNum = 2;
        mainClass.colNum = n;
        mainClass.countNum(0, 0, grid);
        int result = mainClass.getResult();
        System.out.println(result == 0 ? -1 : result);
    }

    // 计数器
    private int cnt = 0;
    public int colNum;
    public int rowNum;
    public int getResult() {
        return cnt;
    }
    /**
     * 递归解法
     *
     * @param x
     * @param y
     * @param grid
     */
    public void countNum(int x, int y, char[][] grid) {
        // 当前位置不合法
        if (!isVaild(x, y) || grid[x][y] == 'X') {
            return;
        }
        // 递归终止条件 -> 到达棋盘右下角
        if (x == rowNum - 1 && y == colNum - 1) {
            cnt++;
            return;
        }
        // 三种走法
        countNum(x, y + 1, grid);
        countNum(x - 1, y + 1, grid);
        countNum(x + 1, y + 1, grid);
    }

    /**
     * 判断当前位置是否合法。
     *
     * @param x
     * @param y
     * @return
     */
    private boolean isVaild(int x, int y) {
        return x >= 0 && x < rowNum && y >= 0 && y < colNum;
    }

}
/* EOF */