package io.ziheng.string.leetcode;
/**
 * LeetCode 459. Repeated Substring Pattern
 * https://leetcode.com/problems/repeated-substring-pattern/
 */
public class RepeatedSubstringPattern {
    public static void main(String[] args) {
        // ...
    }
    public boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        int strLength = s.length();
        for (int i = strLength / 2; i >= 1; i--) {
            int times = strLength / i;
            String subString = s.substring(0, i);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < times; j++) {
                sb.append(subString);
            }
            if (sb.toString().equals(s)) {
                return true;
            }
        }
        return false;
    }
}
/* EOF */