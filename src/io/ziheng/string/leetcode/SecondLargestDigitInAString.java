package io.ziheng.string.leetcode;

/**
 * LeetCode 1796. Second Largest Digit in a String
 * https://leetcode.com/problems/second-largest-digit-in-a-string/
 */
public class SecondLargestDigitInAString {
    public static void main(String[] args) {
        // ...
    }
    public int secondHighest(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        int largetNum = -1;
        int secondLargestNum = Integer.MIN_VALUE;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                int currentNum = s.charAt(i) - '0';
                if (currentNum > largetNum) {
                    secondLargestNum = largetNum;
                    largetNum = currentNum;
                } else if (currentNum > secondLargestNum && currentNum < largetNum) {
                    secondLargestNum = currentNum;
                }
            }
        }
        return secondLargestNum == Integer.MIN_VALUE ? -1 : secondLargestNum;
    }
}

/* EOF */
