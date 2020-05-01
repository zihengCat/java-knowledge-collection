package io.ziheng.array.leetcode;
/**
 * LeetCode 1266. Minimum Time Visiting All Points
 * https://leetcode.com/problems/minimum-time-visiting-all-points/
 */
public class MinimumTimeVisitingAllPoints {
    public int minTimeToVisitAllPoints(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        int resultNum = 0;
        for (int i = 1; i < points.length; i++) {
            int[] currentPoint = points[i];
            int[] previousPoint = points[i - 1];
            resultNum += Math.max(
                Math.abs(currentPoint[0] - previousPoint[0]),
                Math.abs(currentPoint[1] - previousPoint[1])
            );
        }
        return resultNum;
    }
}
/* EOF */