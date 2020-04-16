package io.ziheng.recursion.dynamicprogramming.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;

/**
 * LeetCode 354. Russian Doll Envelopes
 * https://leetcode.com/problems/russian-doll-envelopes/
 */
public class RussianDollEnvelopes {
    public static void main(String[] args) {
        RussianDollEnvelopes obj = new RussianDollEnvelopes();
        int[][] envelopes = new int[][] {
            {5, 4, },
            {6, 4, },
            {6, 7, },
            {2, 3, },
        };
        System.out.println(
            obj.maxEnvelopes(envelopes)
        );
    }
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        // 按照 [w, h] 排序
        Arrays.sort(envelopes,
            new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if (o1[0] == o2[0]) {
                        return o2[1] - o1[1];
                    } else {
                        return o1[0] - o2[0];
                    }
                }
            }
        );
        int n = envelopes.length;
        int[] dp = new int[n];
        int ret = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++){
                if (envelopes[i][0] > envelopes[j][0]
                    && envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            ret = Math.max(ret, dp[i]);
        }
        return ret;
        // return longestIncreasingSequences(
        //     0,
        //     envelopes.length,
        //     Integer.MIN_VALUE,
        //     envelopes
        // );
    }
    private Map<String, Integer> memo = new HashMap<>();
    private int longestIncreasingSequences(
        int currentIndex,
        int maximumLength,
        int previousNum,
        int[][] envelopes) {
        if (currentIndex >= maximumLength) {
            return 0;
        }
        String key = currentIndex + "->" + previousNum;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int taken = 0;
        int notTaken = 0;
        if (envelopes[currentIndex][1] > previousNum) {
            taken = 1 + longestIncreasingSequences(
                currentIndex + 1,
                maximumLength,
                envelopes[currentIndex][1],
                envelopes
            );
        }
        notTaken = longestIncreasingSequences(
            currentIndex + 1,
            maximumLength,
            previousNum,
            envelopes
        );
        memo.put(key, Math.max(taken, notTaken));
        return memo.get(key);
    }
}
/* EOF */