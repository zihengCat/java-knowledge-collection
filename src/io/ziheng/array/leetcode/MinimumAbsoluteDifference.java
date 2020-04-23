package io.ziheng.array.leetcode;

import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * LeetCode 1200. Minimum Absolute Difference
 * https://leetcode.com/problems/minimum-absolute-difference/
 */
public class MinimumAbsoluteDifference {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> resultList = new LinkedList<>();
        Arrays.sort(arr);
        int minimumAbsDifference = findMinimumAbsDifference(arr);
        for (int i = 1; i < arr.length; i++) {
            if (Math.abs(arr[i] - arr[i - 1]) == minimumAbsDifference) {
                List<Integer> aList = new LinkedList<>();
                aList.add(arr[i - 1]);
                aList.add(arr[i]);
                resultList.add(aList);
            }
        }
        return resultList;
    }
    private int findMinimumAbsDifference(int[] sortedArray) {
        int minimumAbsDifference = Integer.MAX_VALUE;
        for (int i = 1; i < sortedArray.length; i++) {
            minimumAbsDifference = Math.min(
                minimumAbsDifference,
                Math.abs(sortedArray[i] - sortedArray[i - 1])
            );
        }
        return minimumAbsDifference;
    }
}
/* EOF */