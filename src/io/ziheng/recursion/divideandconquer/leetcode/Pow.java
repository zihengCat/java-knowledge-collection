package io.ziheng.recursion.divideandconquer.leetcode;
/**
 * LeetCode 50. Pow(x, n)
 * https://leetcode.com/problems/powx-n/
 */
public class Pow {
    public double myPow(double x, int n) {
        if (n < 0) {
            return 1 / powDivideAndConquer(x, n);
        } else {
            return powDivideAndConquer(x, n);
        }
    }
    private double powDivideAndConquer(double x, int n) {
        // 终止条件
        if (n == 0) {
            return 1;
        }
        // 处理逻辑
        double halfResult = powDivideAndConquer(x, n / 2);
        return n % 2 == 0
        ? halfResult * halfResult
        : halfResult * halfResult * x;
    }
}
/* EOF */