package io.ziheng.array.leetcode;
/**
 * LeetCode 154. Find Minimum in Rotated Sorted Array II
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
 */
public class FindMinimumInRotatedSortedArrayII {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int minValue = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < minValue) {
                minValue = nums[i];
            }
        }
        return minValue;
    }
}
/* EOF */