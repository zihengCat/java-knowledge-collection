package io.ziheng.recursion.dynamicprogramming;

public class FibonacciSequence {
    public static void main(String[] args) {
        int n = 8;
        long startTime = System.currentTimeMillis();
        long result1 = FibonacciSequence.fib(n);
        long result2 = FibonacciSequence.fibWithTopToBottom(n);
        long result3 = FibonacciSequence.fibWithBottomToTop(n);
        long endTime = System.currentTimeMillis();
        assert result1 == result2 && result1 == result3;
        System.out.printf(
            "fib(%d): %s, cost %f\n",
            n, String.valueOf(result3), (endTime - startTime) / 1000.0
        );
    }
    /**
     * 斐波那契数列 - 自底向上动态规划。
     *
     * @param n
     * @return int
     */
    public static int fibWithBottomToTop(int n) {
        int[] memory = new int[n + 1];
        for (int i = 0; i < memory.length; i++) {
            memory[i] = -1;
        }
        memory[0] = 0;
        memory[1] = 0;
        for (int i = 2; i <= n; i++) {
            memory[i] = memory[i - 1] + memory[i - 2];
        }
        return memory[n];
    }
    private static final int CAPACITY = 128;
    private static final int[] memory = new int[CAPACITY];
    static {
        for (int i = 0; i < CAPACITY; i++) {
            memory[i] = -1;
        }
    }
    /**
     * 斐波那契数列 - 自顶向下记忆化搜索。
     *
     * @param n
     * @return int
     */
    public static int fibWithTopToBottom(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (memory[n] == -1) {
            memory[n] = fibWithTopToBottom(n - 1) + fibWithTopToBottom(n - 2);
            return memory[n];
        }
        return memory[n];
    }
    /**
     * 斐波那契数列 - 标准递归版本。
     *
     * @param n
     * @return int
     */
    public static int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }
}
/* EOF */