package io.ziheng.recursion.dynamicprogramming.leetcode;
/**
 * LeetCode 1143. Longest Common Subsequence
 * https://leetcode.com/problems/longest-common-subsequence/
 */
public class LongestCommonSubsequence {
    private int[][] memo;
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null) {
            return 0;
        }
        memo = new int[text1.length()][text2.length()];
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[0].length; j++) {
                memo[i][j] = -1;
            }
        }
        return lcsRecursively(text1, text2, 0, 0);
    }
    private int lcsRecursively(String s1, String s2, int i1, int i2) {
        if (i1 >= s1.length() || i2 >= s2.length()) {
            return 0;
        }
        if (memo[i1][i2] != -1) {
            return memo[i1][i2];
        }
        if (s1.charAt(i1) == s2.charAt(i2)) {
            memo[i1][i2] = 1 + lcsRecursively(s1, s2, i1 + 1, i2 + 1);
            return memo[i1][i2];
        } else {
            memo[i1][i2] = Math.max(
                lcsRecursively(s1, s2, i1 + 1, i2),
                lcsRecursively(s1, s2, i1, i2 + 1)
            );
            return memo[i1][i2];
        }
    }
}
/* EOF */