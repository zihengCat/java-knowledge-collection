package io.ziheng.others.leetcode;

/**
 * LeetCode 1304. Find N Unique Integers Sum up to Zero
 * https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero/
 */
public class FindNUniqueIntegersSumUpToZero {
    public int[] sumZero(int n) {
        if (n <= 0) {
            return new int[0];
        }
        int[] resultArray = new int[n];
        int sum = 0;
        for (int i = 0; i < n - 1; i++) {
            resultArray[i] = i + 1;
            sum += i + 1;
        }
        resultArray[n - 1] = -1 * sum;
        return resultArray;
    }
}
/* EOF */