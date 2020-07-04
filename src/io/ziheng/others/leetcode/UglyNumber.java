package io.ziheng.others.leetcode;
/**
 * LeetCode 263. Ugly Number
 * https://leetcode.com/problems/ugly-number/
 */
public class UglyNumber {
    public static void main(String[] args) {
        // ...
    }
    public boolean isUgly(int num) {
        // edge cases -> 0
        if (num <= 0) {
            return false;
        }
        // return isUglyIteratively(num);
        return isUglyRecursively(num);
    }
    private boolean isUglyIteratively(int n) {
        int[] arr = new int[]{2, 3, 5, };
        for (int i : arr) {
            while (n % i == 0) {
                n /= i;
            }
        }
        return n == 1;
    }
    private boolean isUglyRecursively(int n) {
        if (n == 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        if (n % 2 == 0) {
            return isUglyRecursively(n / 2);
        }
        if (n % 3 == 0) {
            return isUglyRecursively(n / 3);
        }
        if (n % 5 == 0) {
            return isUglyRecursively(n / 5);
        }
        return false;
    }
}
/* EOF */