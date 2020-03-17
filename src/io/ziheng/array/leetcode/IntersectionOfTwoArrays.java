package io.ziheng.array.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 349. Intersection of Two Arrays
 * https://leetcode.com/problems/intersection-of-two-arrays/
 */
public class IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return null;
        }
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();
        for (int num : nums1) {
            setA.add(num);
        }
        for (int num : nums2) {
            setB.add(num);
        }
        return setIntersection(setA, setB);
    }
    private int[] setIntersection(Set<Integer> setA, Set<Integer> setB) {
        int[] arr = new int[
            setA.size() > setB.size()
            ? setA.size()
            : setB.size()
        ];
        int index = 0;
        for (int num : setA) {
            if (setB.contains(num)) {
                arr[index] = num;
                index++;
            }
        }
        return Arrays.copyOf(arr, index);
    }
}
/* EOF */