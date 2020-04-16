package io.ziheng.string.leetcode;
/**
 * LeetCode 520. Detect Capital
 * https://leetcode.com/problems/detect-capital/
 */
public class DetectCapital {
    public boolean detectCapitalUse(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        char[] arr = word.toCharArray();
        if (allUpperCase(arr)
            || allLowerCase(arr)
            || firstUpperCase(arr)) {
            return true;
        }
        return false;
    }
    private boolean firstUpperCase(char[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (!Character.isLowerCase(arr[i])) {
                return false;
            }
        }
        return Character.isUpperCase(arr[0]);
    }
    private boolean allUpperCase(char[] arr) {
        for (char c : arr) {
            if (!Character.isUpperCase(c)) {
                return false;
            }
        }
        return true;
    }
    private boolean allLowerCase(char[] arr) {
        for (char c : arr) {
            if (!Character.isLowerCase(c)) {
                return false;
            }
        }
        return true;
    }
}
/* EOF */