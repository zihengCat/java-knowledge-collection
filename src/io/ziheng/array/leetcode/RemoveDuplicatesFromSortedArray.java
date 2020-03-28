package io.ziheng.array.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * LeetCode 26. Remove Duplicates from Sorted Array
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 */
public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Set<Integer> sortedSet = new TreeSet<>();
        for (int n : nums) {
            sortedSet.add(n);
        }
        List<Integer> list = sortedSet.stream().collect(Collectors.toList());
        Arrays.fill(nums, 0);
        for (int i = 0; i < list.size(); i++) {
            nums[i] = list.get(i);
        }
        return list.size();
    }
}
/* EOF */