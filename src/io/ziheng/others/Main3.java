
import java.util.Arrays;
import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        // // 数据读入
        // Scanner scanner = new Scanner(System.in);
        // // col * row
        // String firstLine = scanner.nextLine();
        // int colNum = Integer.valueOf(
        //     firstLine.split(" ")[0]
        // );
        // int rowNum = Integer.valueOf(
        //     firstLine.split(" ")[1]
        // );
        // int[][] grid = new int[rowNum][colNum];
        // for (int i = 0; i < rowNum; i++) {
        //     String line = scanner.nextLine();
        //     for (int j = 0; j < colNum; j++) {
        //         grid[i][j] = Integer.valueOf(
        //             line.split(" ")[j]
        //         );
        //     }
        // }

        // 测试数据
        int rowNum = 3;
        int colNum = 4;
        int[][] grid = {
            {1, 0, -1, 1, },
            {-2, 0, -1, -3, },
            {2, 2, 0, 0, },
        };
        // 起点 终点
        int startX = 0;
        int startY = 0;
        int endX = 0;
        int endY = 0;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (grid[i][j] == -2) {
                    startX = i;
                    startY = j;
                } else if (grid[i][j] == -3) {
                    endX = i;
                    endY = j;
                }
            }
        }
        System.out.println("Start: " + startX + ", " + startY);
        System.out.println("End: " + endX + ", " + endY);
        boolean[][] visited = new boolean[rowNum][colNum];
        // 走迷宫
        findPath(startX, startY, endX, endY, 0, grid, visited);
        System.out.println(minNum == Integer.MAX_VALUE ? -1 : minNum);
    }
    private static int minNum = Integer.MAX_VALUE;
    private static void findPath(
        int startX, int startY,
        int endX, int endY,
        int pathNum,
        int[][] grid,
        boolean[][] visited
    ) {
        // System.out.println(startX + ", " + startY + " -> " + pathNum);
        // try {
        //     Thread.sleep(100L);
        // } catch (Exception e) {
        //     //TODO: handle exception
        // }
        if (!isVaild(startX, startY, grid)) {
            return;
        }
        if (visited[startX][startY]) {
            return;
        }
        visited[startX][startY] = true;
        if (startX == endX && startY == endY) {
            minNum = Math.min(minNum, pathNum);
            return;
        }
        // 传送门
        if (grid[startX][startY] > 0) {
            int[] next = findNext(startX, startY, grid);
            findPath(next[0], next[1], endX, endY, pathNum + 1, grid, visited);
        } else {
            findPath(startX, startY + 1, endX, endY, pathNum + 1, grid, visited);
            findPath(startX + 1, startY, endX, endY, pathNum + 1, grid, visited);
            findPath(startX, startY - 1, endX, endY, pathNum + 1, grid, visited);
            findPath(startX - 1, startY, endX, endY, pathNum + 1, grid, visited);
        }

        visited[startX][startY] = false;
    }
    private static boolean isVaild(int i, int j, int[][] grid) {
        return i >= 0 && i < grid.length
            && j >= 0 && j < grid[0].length
            && grid[i][j] != -1;
    }
    private static int[] findNext(int x, int y, int[][] grid) {
        int target = grid[x][y];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == target
                    && (i != x || j != y)) {
                    int[] arr = new int[2];
                    arr[0] = i;
                    arr[1] = j;
                    return arr;
                }
            }
        }
        return null;
    }
}