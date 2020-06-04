package io.ziheng.graph.leetcode;

import java.util.Arrays;

/**
 * LeetCode 1029. Two City Scheduling
 * https://leetcode.com/problems/two-city-scheduling/
 */
public class TwoCityScheduling {
    public int twoCitySchedCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int minCost = 0;
        int[] refund = new int[costs.length];
        int index = 0;
        for (int[] cost : costs) {
            minCost += cost[0];
            refund[index] = cost[1] - cost[0];
            index++;
        }
        Arrays.sort(refund);
        for (int i = 0; i < costs.length / 2; i++) {
            minCost += refund[i];
        }
        return minCost;
    }
}
/* EOF */