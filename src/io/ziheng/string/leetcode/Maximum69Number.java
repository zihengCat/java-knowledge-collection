package io.ziheng.string.leetcode;
/**
 * LeetCode 1323. Maximum 69 Number
 * https://leetcode.com/problems/maximum-69-number/
 */
public class Maximum69Number {
    public int maximum69Number (int num) {
        char[] arr = String.valueOf(num).toCharArray();
        int maxNum = num;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = change(arr[i]);
            maxNum = Math.max(
                maxNum,
                Integer.valueOf(
                    String.valueOf(arr)
                )
            );
            arr[i] = change(arr[i]);
        }
        return maxNum;
    }
    private char change(char c) {
        return c == '6' ? '9' : '6';
    }
}
/* EOF */