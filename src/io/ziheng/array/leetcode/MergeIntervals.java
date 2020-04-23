package io.ziheng.array.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * LeetCode 56. Merge Intervals
 * https://leetcode.com/problems/merge-intervals/
 */
public class MergeIntervals {
    /**
     * 主函数 -> 测试用例
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        int[][] intervals = {
            {2, 6, },
            {1, 3, },
            {15,18, },
            {8, 10, },
        };
        MergeIntervals obj = new MergeIntervals();
        // System.out.println(
        //     Arrays.deepToString(intervals)
        // );
        System.out.println(
            Arrays.deepToString(
                obj.merge(intervals)
            )
        );
    }
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][0];
        }
        Arrays.sort(intervals,
            new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            }
        );
        List<int[]> resultList = new LinkedList<>();
        int[] currentInterval = intervals[0];
        resultList.add(currentInterval);
        for (int[] interval : intervals) {
            // Overlapping intervals, move the end if needed
            // Disjoint intervals, add the new interval to the list
            if (interval[0] <= currentInterval[1]) {
                currentInterval[1] = Math.max(currentInterval[1], interval[1]);
            } else {
                currentInterval = interval;
                resultList.add(currentInterval);
            }
        }
        return resultList.toArray(new int[resultList.size()][]);
    }
}
/* EOF */