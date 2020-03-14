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
            "fib(%d): %d\n"
          + "fibTopToBottom(%d): %d\n"
          + "fibBottomToTop(%d): %d\n"
          + "cost: %f\n",
            n, result1,
            n, result2,
            n, result3,
            (endTime - startTime) / 1000.0
        );
    }
    /**
     * 斐波那契数列 - 自底向上动态规划。
     *
     * @param n
     * @return int
     */
    public static int fibWithBottomToTop(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
    private static final int CAPACITY = 128;
    private static final int[] memo = new int[CAPACITY];
    static {
        for (int i = 0; i < CAPACITY; i++) {
            memo[i] = -1;
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
        if (memo[n] != -1) {
            return memo[n];
        }
        memo[n] = fibWithTopToBottom(n - 1) + fibWithTopToBottom(n - 2);
        return memo[n];
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