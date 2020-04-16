package io.ziheng.array.leetcode;
/**
 * LeetCode 81. Search in Rotated Sorted Array II
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 */
public class SearchInRotatedSortedArrayII {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        for (int num : nums) {
            if (num == target) {
                return true;
            }
        }
        return false;
    }
}
/* EOF */