package io.ziheng.array.leetcode;

import java.util.Map;
import java.util.LinkedHashMap;

/**
 * LeetCode 80. Remove Duplicates from Sorted Array II
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
 */
public class RemoveDuplicatesFromSortedArrayII {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return removeDuplicatesHashing(nums);
    }
    private int removeDuplicatesHashing(int[] nums) {
        Map<Integer, Integer> aMap = new LinkedHashMap<>();
        for (int num : nums) {
            aMap.put(num, 1 + aMap.getOrDefault(num, 0));
        }
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : aMap.entrySet()) {
            int num = entry.getKey().intValue();
            int n = entry.getValue().intValue();
            if (n > 2) {
                n = 2;
            }
            for (int i = 0; i < n; i++) {
                nums[index] = num;
                index++;
            }
        }
        return index;
    }
}
/* EOF */