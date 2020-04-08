package io.ziheng.others.leetcode;
/**
 * LeetCode 172. Factorial Trailing Zeroes
 * https://leetcode.com/problems/factorial-trailing-zeroes/
 */
public class FactorialTrailingZeroes {
    public int trailingZeroes(int n) {
        // 传统计算
        // return findTrailingZeroes(findFactorial(n));
        // 数学方法
        return trailingZeroesMathWay(n);
    }
    private int trailingZeroesMathWay(int n) {
        int count = 0;
        // Keep dividing n by powers
        // of 5 and update count
        for (long i = 5; n / i > 0; i *= 5) {
            count += n / i;
        }
        return count;
    }
    private int findTrailingZeroes(long n) {
        int cnt = 0;
        while (n > 0 && n % 10 != 0) {
            cnt++;
            n /= 10;
        }
        return cnt;
    }
    private long findFactorial(int n) {
        if (n <= 0) {
            return 1L;
        }
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
/* EOF */