package io.ziheng.array.leetcode;
/**
 * LeetCode 1295. Find Numbers with Even Number of Digits
 * https://leetcode.com/problems/find-numbers-with-even-number-of-digits/
 */
public class FindNumbersWithEvenNumberOfDigits {
    public int findNumbers(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int result = 0;
        for (int num : nums) {
            if (isEvenNum(num)) {
                result++;
            }
        }
        return result;
    }
    private boolean isEvenNum(int num) {
        int cnt = 0;
        while (num > 0) {
            num /= 10;
            cnt++;
        }
        return cnt % 2 == 0 ? true : false;
    }
}
/* EOF */