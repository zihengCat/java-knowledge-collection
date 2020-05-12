package io.ziheng.array.leetcode;

import java.util.Map;
import java.util.HashMap;

/**
 * LeetCode 540. Single Element in a Sorted Array
 * https://leetcode.com/problems/single-element-in-a-sorted-array/
 */
public class SingleElementInASortedArray {
    public int singleNonDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // return singleNonDuplicateHashing(nums);
        return singleNonDuplicateBinarySearch(nums);
    }
    private int singleNonDuplicateBinarySearch(int[] nums) {
        int lowerBound = 0;
        int upperBound = nums.length - 1;
        while (lowerBound < upperBound) {
            int mid = lowerBound + (upperBound - upperBound) / 2;
            if (mid % 2 == 0) {
                // even
                if (nums[mid] == nums[mid + 1]) {
                    lowerBound = mid + 2;
                } else {
                    upperBound = mid;
                }
            } else {
                // odd
                if (nums[mid] == nums[mid - 1]) {
                    lowerBound = mid + 1;
                } else {
                    upperBound = mid;
                }
            }
        }
        return nums[lowerBound];
    }
    private int singleNonDuplicateHashing(int[] nums) {
        Map<Integer, Integer> aMap = new HashMap<>();
        for (int n : nums) {
            aMap.put(n, 1 + aMap.getOrDefault(n, 0));
        }
        for (Map.Entry<Integer, Integer> entry : aMap.entrySet()) {
            if (entry.getValue().intValue() == 1) {
                return entry.getKey().intValue();
            }
        }
        return 0;
    }
}
/* EOF */