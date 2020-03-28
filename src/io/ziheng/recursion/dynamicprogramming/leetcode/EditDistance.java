package io.ziheng.recursion.dynamicprogramming.leetcode;

import java.util.Arrays;

/**
 * LeetCode 72. Edit Distance
 * https://leetcode.com/problems/edit-distance/
 */
public class EditDistance {
    /**
     * 主函数 -> 测试用例
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        EditDistance obj = new EditDistance();
        String str1 = "sunday";
        String str2 = "saturday";
        System.out.println(
            obj.minDistance(str1, str2)
        );
        String word1 = "intention";
        String word2 = "execution";
        System.out.println(
            obj.minDistance(word1, word2)
        );
    }
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }

        // 暴力递归
        // return minDistanceRecursively(word1, word2, word1.length(), word2.length());

        // 记忆化搜索
        int[][] memo = new int[word1.length() + 1][word2.length() + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return minDistanceTopToBottom(word1, word2, word1.length(), word2.length(), memo);
    }
    private int minDistanceTopToBottom(String s1, String s2, int len1, int len2, int[][] memo) {
        if (len1 == 0) {
            return len2;
        }
        if (len2 == 0) {
            return len1;
        }
        if (memo[len1][len2] == -1) {
            if (s1.charAt(len1 - 1) == s2.charAt(len2 - 1)) {
                memo[len1][len2] = minDistanceTopToBottom(s1, s2, len1 - 1, len2 - 1, memo);
            } else {
                memo[len1][len2] = 1 + Math.min(
                    Math.min(
                        minDistanceTopToBottom(s1, s2, len1, len2 - 1, memo),
                        minDistanceTopToBottom(s1, s2, len1 - 1, len2, memo)
                    ),
                    minDistanceTopToBottom(s1, s2, len1 - 1, len2 - 1, memo)
                );
            }
        }
        return memo[len1][len2];
    }
    private int minDistanceRecursively(String s1, String s2, int len1, int len2) {
        if (len1 == 0) {
            return len2;
        }
        if (len2 == 0) {
            return len1;
        }
        if (s1.charAt(len1 - 1) == s2.charAt(len2 - 1)) {
            return minDistanceRecursively(s1, s2, len1 - 1, len2 - 1);
        }
        return 1 + Math.min(
            Math.min(
                // Insert
                minDistanceRecursively(s1, s2, len1, len2 - 1),
                // Remove
                minDistanceRecursively(s1, s2, len1 - 1, len2)
            ),
                // Replace
            minDistanceRecursively(s1, s2, len1 - 1, len2 - 1)
        );
    }
}
/* EOF */