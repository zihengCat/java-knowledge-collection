package io.ziheng.recursion.dynamicprogramming;

public class FibonacciSequence {
    public static void main(String[] args) {
        int n = 8;
        long startTime = System.currentTimeMillis();
        long result1 = FibonacciSequence.fib(n);
        long result2 = FibonacciSequence.fibV2(n);
        long result3 = FibonacciSequence.fibV3(n);
        long endTime = System.currentTimeMillis();
        assert result1 == result2 && result1 == result3;
        System.out.printf(
            "fib(%d): %s, cost %f\n",
            n, String.valueOf(result3), (endTime - startTime) / 1000.0
        );
    }
    private static final int CAPACITY = 128;
    private static final int[] memory = new int[CAPACITY];
    static {
        for (int i = 0; i < CAPACITY; i++) {
            memory[i] = -1;
        }
    }
    public static int fibV3(int n) {
        int[] memory = new int[n + 1];
        for (int i = 0; i < memory.length; i++) {
            memory[i] = -1;
        }
        memory[0] = 0;
        memory[1] = 1;
        for (int i = 2; i <= n; i++) {
            memory[i] = memory[i - 1] + memory[i - 2];
        }
        return memory[n];
    }
    public static int fibV2(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (memory[n] == -1) {
            memory[n] = fibV2(n - 1) + fibV2(n - 2);
        }
        return memory[n];
    }
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