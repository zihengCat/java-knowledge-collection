package io.ziheng.others.leetcode;

/**
 * LeetCode 1232. Check If It Is a Straight Line
 * https://leetcode.com/problems/check-if-it-is-a-straight-line/
 */
public class CheckIfItIsAStraightLine {
    public boolean checkStraightLine(int[][] coordinates) {
        if (coordinates == null || coordinates.length < 2) {
            return true;
        }
        int xA = coordinates[0][0];
        int yA = coordinates[0][1];
        int xB = coordinates[1][0];
        int yB = coordinates[1][1];
        int dx = xB - xA;
        int dy = yB - yA;
        for (int[] point : coordinates) {
            int x = point[0];
            int y = point[1];
            if (dx * (y - yA) != dy * (x - xA)) {
                return false;
            }
        }
        return true;
    }
}
/* EOF */