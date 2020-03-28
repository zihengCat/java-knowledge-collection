package io.ziheng.recursion.dynamicprogramming.leetcode;

import java.util.Arrays;

/**
 * LeetCode 1143. Longest Common Subsequence
 * https://leetcode.com/problems/longest-common-subsequence/
 */
public class LongestCommonSubsequence {
    /**
     * 主函数 -> 测试用例
     */
    public static void main(String[] args) {
        LongestCommonSubsequence obj = new LongestCommonSubsequence();
        String s1 = "abcde";
        String s2 = "ace";
        System.out.println(
            obj.longestCommonSubsequence(s1, s2)
        );
        s1 = "abc";
        s2 = "def";
        System.out.println(
            obj.longestCommonSubsequence(s1, s2)
        );
    }
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null) {
            return 0;
        }
        // return longestCommonSubsequenceRecursively(text1, text2, 0, 0);
        return longestCommonSubsequenceTopToBottom(text1, text2);
    }
    /**
     * 自顶向下 -> 记忆化搜索
     *
     * @param text1
     * @param text2
     * @return int
     */
    private int longestCommonSubsequenceTopToBottom(String s1, String s2) {
        int[][] memo = new int[s1.length() + 1][s2.length() + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return longestCommonSubsequenceTopToBottom(s1, s2, 0, 0, memo);
    }
    private int longestCommonSubsequenceTopToBottom(String s1, String s2, int i1, int i2, int[][] memo) {
        if (i1 >= s1.length() || i2 >= s2.length()) {
            return 0;
        }
        if (memo[i1][i2] == -1) {
            if (s1.charAt(i1) == s2.charAt(i2)) {
                memo[i1][i2] = 1 + longestCommonSubsequenceTopToBottom(
                    s1, s2, i1 + 1, i2 + 1, memo
                );
            } else {
                memo[i1][i2] = Math.max(
                    longestCommonSubsequenceTopToBottom(s1, s2, i1 + 1, i2, memo),
                    longestCommonSubsequenceTopToBottom(s1, s2, i1, i2 + 1, memo)
                );
            }
        }
        return memo[i1][i2];
    }
    /**
     * 暴力递归
     *
     * @param s1
     * @param s2
     * @param i1
     * @param i2
     * @return int
     */
    private int longestCommonSubsequenceRecursively(String s1, String s2, int i1, int i2) {
        // 递归到底
        if (i1 >= s1.length() || i2 >= s2.length()) {
            return 0;
        }
        if (s1.charAt(i1) == s2.charAt(i2)) {
            return 1 + longestCommonSubsequenceRecursively(
                s1, s2, i1 + 1 , i2 + 1
            );
        } else {
            return Math.max(
                longestCommonSubsequenceRecursively(
                    s1, s2, i1 + 1 , i2
                ),
                longestCommonSubsequenceRecursively(
                    s1, s2, i1, i2 + 1
                )
            );
        }
    }
}
/* EOF */