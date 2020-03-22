package io.ziheng.hashtable.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.List;

/**
 * LeetCode 350. Intersection of Two Arrays II
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/
 */
public class IntersectionOfTwoArraysII {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0
            || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        List<Integer> resultList = new LinkedList<>();
        Map<Integer, Integer> mapA = new HashMap<>();
        Map<Integer, Integer> mapB = new HashMap<>();
        for (int num : nums1) {
            if (!mapA.containsKey(num)) {
                mapA.put(num, 1);
            } else {
                mapA.put(num, 1 + mapA.get(num));
            }
        }
        for (int num : nums2) {
            if (!mapB.containsKey(num)) {
                mapB.put(num, 1);
            } else {
                mapB.put(num, 1 + mapB.get(num));
            }
        }
        for (Integer num : mapA.keySet()) {
            if (mapB.containsKey(num)) {
                int min = Math.min(mapA.get(num), mapB.get(num));
                for (int i = 0; i < min; i++) {
                    resultList.add(num);
                }
            }
        }
        int[] resultArray = new int[resultList.size()];
        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = resultList.get(i);
        }
        return resultArray;
    }
}
/* EOF */