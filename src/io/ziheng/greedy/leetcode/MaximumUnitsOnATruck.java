package io.ziheng.greedy.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * LeetCode 1710. Maximum Units on a Truck
 * https://leetcode.com/problems/maximum-units-on-a-truck/
 */
public class MaximumUnitsOnATruck {
    public static void main(String[] args) {
        MaximumUnitsOnATruck obj = new MaximumUnitsOnATruck();
        int[][] boxTypes = new int[][]{
            {2, 2, },
            {1, 3, },
            {3, 1, },
        };
        int truckSize = 4;
        System.out.println(obj.maximumUnits(boxTypes, truckSize));
        System.out.println(Arrays.deepToString(boxTypes));
    }
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        if (boxTypes == null || truckSize < 0) {
            return 0;
        }
        Arrays.sort(boxTypes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
        int units = 0;
        for (int[] boxType : boxTypes) {
            if (truckSize >= boxType[0]) {
                units += boxType[0] * boxType[1];
                truckSize -= boxType[0];
            } else {
                units += truckSize * boxType[1];
                truckSize -= truckSize;
                return units;
            }
        }
        return units;
    }
}

/* EOF */
