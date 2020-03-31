package io.ziheng.hashtable.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1331. Rank Transform of an Array
 * https://leetcode.com/problems/rank-transform-of-an-array/
 */
public class RankTransformOfAnArray {
    public int[] arrayRankTransform(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int[] resultArray = Arrays.copyOf(arr, arr.length);
        Arrays.sort(resultArray);
        Map<Integer, Integer> rankMap = new HashMap<>();
        for (int n : resultArray) {
            if (!rankMap.containsKey(n)) {
                rankMap.put(n, 1 + rankMap.size());
            }
        }
        for (int i = 0; i < arr.length; i++) {
            resultArray[i] = rankMap.get(arr[i]);
        }
        return resultArray;
    }
}
/* EOF */