package io.ziheng.others.leetcode;
/**
 * LeetCode 1317. Convert Integer to the Sum of Two No-Zero Integers
 * https://leetcode.com/problems/convert-integer-to-the-sum-of-two-no-zero-integers/
 */
public class ConvertIntegerToTheSumOfTwoNoZeroIntegers {
    public int[] getNoZeroIntegers(int n) {
        for (int i = 1; i < n; i++) {
            if (isNoZeroInteger(i)
                && isNoZeroInteger(n - i)) {
                return new int[]{i, n - i};
            }
        }
        return new int[0];
    }
    private boolean isNoZeroInteger(int n) {
        while (n > 0) {
            if (n % 10 == 0) {
                return false;
            }
            n /= 10;
        }
        return true;
    }
}
/* EOF */