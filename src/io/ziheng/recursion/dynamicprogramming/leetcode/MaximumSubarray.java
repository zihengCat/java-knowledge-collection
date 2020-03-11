package io.ziheng.recursion.dynamicprogramming.leetcode;
/**
 * LeetCode 53. Maximum Subarray
 * https://leetcode.com/problems/maximum-subarray/
 */
public class MaximumSubarray {
    private int maxValue = Integer.MIN_VALUE;
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        maxSubArray0(nums, nums.length - 1);
        return maxValue;
    }
    private int maxSubArray0(int[] nums, int right) {
        if (right == 0) {
            maxValue = nums[0];
            return nums[0];
        }
        int tmp = nums[right] + maxSubArray0(nums, right - 1);
        int sum = Math.max(tmp, nums[right]);
        maxValue = Math.max(maxValue, sum);
        return sum;
    }
}
/* EOF */