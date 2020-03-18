package io.ziheng.string.leetcode;
/**
 * LeetCode 541. Reverse String II
 * https://leetcode.com/problems/reverse-string-ii/
 */
public class ReverseStringII {
    public String reverseStr(String s, int k) {
        if (s == null || s.length() < 2 || k < 0) {
            return s;
        }
        char[] charArray = s.toCharArray();
        int i = 0;
        int n = charArray.length;
        while (i < n) {
            int end = Math.min(i + k - 1, n - 1);
            reverseStr0(charArray, i, end);
            i += 2 * k;
        }
        return String.valueOf(charArray);
    }
    private void reverseStr0(char[] s, int left, int right) {
        while (left < right) {
            swap(s, left, right);
            left++;
            right--;
        }
    }
    private void swap(char[] s, int i, int j) {
        char c = s[i];
        s[i] = s[j];
        s[j] = c;
    }
}
/* EOF */