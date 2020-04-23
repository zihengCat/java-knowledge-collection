package io.ziheng.search.leetcode;

/**
 * LeetCode 153. Find Minimum in Rotated Sorted Array
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 */
public class FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        // return findMinBruteForce(nums);
        return findMinBinarySearch(nums);
    }

    private int findMinBinarySearch(int[] nums) {
        return Math.min(nums[0], nums[findBreakingPoint(nums)]);
    }

    private int findBreakingPoint(int[] nums) {
        int breakingPoint = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                return i;
            }
        }
        return breakingPoint;
    }

    private int findMinBruteForce(int[] nums) {
        int minimumElement = nums[0];
        for (int num : nums) {
            if (num < minimumElement) {
                minimumElement = num;
            }
        }
        return minimumElement;
    }
}
/* EOF */