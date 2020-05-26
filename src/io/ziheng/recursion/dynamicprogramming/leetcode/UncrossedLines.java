package io.ziheng.recursion.dynamicprogramming.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1035. Uncrossed Lines
 * https://leetcode.com/problems/uncrossed-lines/ 
 */
public class UncrossedLines {
    public static void main(String[] args) {
        UncrossedLines obj = new UncrossedLines();
        int[] arrA = {2, 5, 1, 2, 5, };
        int[] arrB = {10, 5, 2, 1, 5, 2, };
        System.out.println(
            obj.maxUncrossedLines(arrA, arrB)
        );
    }
    public int maxUncrossedLines(int[] arrA, int[] arrB) {
        if (arrA == null || arrA.length == 0
            || arrB == null || arrB.length == 0) {
            return 0;
        }
        return maxUncrossedLinesTopToBottom(arrA, arrB, 0, 0, new HashMap<>());
    }
    private int maxUncrossedLinesTopToBottom(
        int[] arrA, int[] arrB,
        int indexA, int indexB,
        Map<String, Integer> memo) {
        if (indexA >= arrA.length || indexB >= arrB.length) {
            return 0;
        }
        String key = indexA + "-" + indexB;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int currentResult = 0;
        if (arrA[indexA] == arrB[indexB]) {
            currentResult = 1 + maxUncrossedLinesTopToBottom(
                arrA, arrB,
                1 + indexA,
                1 + indexB,
                memo
            );
        } else {
            currentResult = Math.max(
                maxUncrossedLinesTopToBottom(arrA, arrB, 1 + indexA, indexB, memo),
                maxUncrossedLinesTopToBottom(arrA, arrB, indexA, 1 + indexB, memo)
            );
        }
        memo.put(key, currentResult);
        return currentResult;
    }
}
/* EOF */