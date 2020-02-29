package io.ziheng.string.leetcode;
/**
 * LeetCode 344. Reverse String
 * https://leetcode.com/problems/reverse-string/
 */
public class ReverseString {
    public void reverseString(char[] s) {
        if (s == null || s.length < 2) {
            return;
        }
        int pLeft = 0;
        int pRight = s.length - 1;
        while (pLeft < pRight) {
            swap(s, pLeft, pRight);
            pLeft++;
            pRight--;
        }
    }
    private void swap(char[] s, int i, int j) {
        char c = s[i];
        s[i] = s[j];
        s[j] = c;
    }
}
/* EOF */