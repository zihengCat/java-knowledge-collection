package io.ziheng.recursion.divideandconquer.leetcode;
/**
 * LeetCode 50. Pow(x, n)
 * https://leetcode.com/problems/powx-n/
 */
public class Pow {
    public double myPow(double x, int n) {
        if (n < 0) {
            return 1 / powDivideAndConquer(x, n);
        }
        return powDivideAndConquer(x, n);
    }
    private double powBruteForce(double x, int n) {
        double r = 1;
        for (; n > 0; n--) {
            r = r * x;
        }
        return r;
    }
    private double powDivideAndConquer(double x, int n) {
        if (n == 0) {
            return 1;
        }
        double halfResult = powDivideAndConquer(x, n / 2);
        return n % 2 == 0
            ? halfResult * halfResult
            : halfResult * halfResult * x;
    }
}
/* EOF */