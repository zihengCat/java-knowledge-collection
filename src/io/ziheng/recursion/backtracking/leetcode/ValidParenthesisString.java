package io.ziheng.recursion.backtracking.leetcode;
/**
 * LeetCode 678. Valid Parenthesis String
 * https://leetcode.com/problems/valid-parenthesis-string/
 */
public class ValidParenthesisString {
    public boolean checkValidString(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        return checkValidString0(s, 0, 0);
    }
    private boolean checkValidString0(String s, int currentIndex, int count) {
        if (currentIndex >= s.length()) {
            return count == 0 ? true : false;
        }
        if (count < 0) {
            return false;
        }
        if (s.charAt(currentIndex) == '(') {
            return checkValidString0(s, currentIndex + 1, count + 1);
        } else if (s.charAt(currentIndex) == ')') {
            return checkValidString0(s, currentIndex + 1, count - 1);
        } else if (s.charAt(currentIndex) == '*') {
            return checkValidString0(s, currentIndex + 1, count + 1)
                || checkValidString0(s, currentIndex + 1, count)
                || checkValidString0(s, currentIndex + 1, count - 1);
        } else {
            return false;
        }
    }
}
/* EOF */