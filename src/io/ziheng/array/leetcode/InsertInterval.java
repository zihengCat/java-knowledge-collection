package io.ziheng.array.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 57. Insert Interval
 * https://leetcode.com/problems/insert-interval/
 */
public class InsertInterval {
    public static void main(String[] args) {
        InsertInterval obj = new InsertInterval();
        int[][] intervals = new int[][]{
            {1, 3, },
            {6, 9, },
        };
        int[] newInterval = new int[]{2, 5, };
        // [[1,5],[6,9]]
        System.out.println(Arrays.deepToString(
            obj.insert(intervals, newInterval)
        ));
    }
    private static int START = 0;
    private static int END = 1;
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null
            || newInterval == null || newInterval.length  == 0) {
            return new int[0][0];
        }
        List<int[]> rList = new ArrayList<>();
        int i = 0;
        int size = intervals.length;
        int start = newInterval[0];
        int end = newInterval[1];
        while (i < size && intervals[i][END] < start) {
            rList.add(intervals[i]);
            i++;
        }
        while (i < size && intervals[i][START] <= end) {
            start = Math.min(start, intervals[i][START]);
            end = Math.max(end, intervals[i][END]);
            i++;
        }
        rList.add(new int[]{start, end});
        while (i < size) {
            rList.add(intervals[i]);
            i++;
        }
        int[][] rArray = new int[rList.size()][2];
        for (int idx = 0; idx < rList.size(); idx++) {
            rArray[idx] = rList.get(idx);
        }
        return rArray;
    }
}
/* EOF */