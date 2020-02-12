package io.ziheng.recursion.dynamicprogramming.leetcode;
/**
 * LeetCode 70. Climbing Stairs
 * https://leetcode.com/problems/climbing-stairs/
 */
public class ClimbingStairs {

    public int climbStairsV2(int n) {
        if (n < 0) {
            return -1;
        }
        if (n == 0) {
            return 0;
        }
        int[] memory = new int[n + 1];
        memory[0] = 1;
        memory[1] = 1;
        for (int i = 2; i <= n; i++) {
            memory[i] = memory[i - 1] + memory[i - 2];
        }
        return memory[n];
    }
    public int climbStairsV1(int n) {
        if (n < 0) {
            return 0;
        }
        return climbStairs0(n);
    }
    private int climbStairs0(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        //if (n == 1) {
        //    return 1;
        //}
        //if (n == 2) {
        //    return 2;
        //}
        return climbStairs0(n - 1) + climbStairs0(n - 2);
    }
}
/* EOF */