package io.ziheng.hashtable.leetcode;

import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;

/**
 * LeetCode 287. Find the Duplicate Number
 * https://leetcode.com/problems/find-the-duplicate-number/
 */
public class FindTheDuplicateNumber {
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length < 1) {
            return -1;
        }
        // return findDuplicateSortWay(nums);
        return findDuplicateHashWay(nums);
    }
    public int findDuplicateSortWay(int[] nums) {
        int[] sortedNums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sortedNums);
        for (int i = 1; i < nums.length; i++) {
            if (sortedNums[i] == sortedNums[i - 1]) {
                return sortedNums[i];
            }
        }
        return -1;
    }
    public int findDuplicateHashWay(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.contains(num)) {
                set.add(num);
            } else {
                return num;
            }
        }
        return -1;
    }
}
/* EOF */