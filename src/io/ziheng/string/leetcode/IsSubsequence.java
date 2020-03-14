package io.ziheng.string.leetcode;
/**
 * LeetCode 392. Is Subsequence
 * https://leetcode.com/problems/is-subsequence/
 */
public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return false;
        }
        return isSubsequence0(s, t);
    }
    private boolean isSubsequence0(String s, String t) {
        int sIndex = 0;
        int tIndex = 0;
        for (; tIndex != t.length(); tIndex++) {
            if (s.charAt(sIndex) == t.charAt(tIndex)) {
                sIndex++;
                if (sIndex == s.length()) {
                    return true;
                }
            }
        }
        return false;
    }
}
/* EOF */