package io.ziheng.string.leetcode;
/**
 * LeetCode 392. Is Subsequence
 * https://leetcode.com/problems/is-subsequence/
 */
public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        // return isSubsequence0(s, t);
        return isSubsequenceBruteForce(s, t);
    }
    private boolean isSubsequenceBruteForce(String s, String t) {
        int i = -1;
        for (char c : s.toCharArray()) {
            i = t.indexOf(c, i + 1);
            if (i == -1) {
                return false;
            }
        }
        return true;
    }
    private boolean isSubsequence0(String s, String t) {
        int sIndex = 0;
        int tIndex = 0;
        for (/* ... */; tIndex != t.length(); tIndex++) {
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