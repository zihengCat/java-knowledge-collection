package io.ziheng.array.leetcode;
/**
 * LeetCode 643. Maximum Average Subarray I
 * https://leetcode.com/problems/maximum-average-subarray-i/
 */
public class MaximumAverageSubarrayI {
    public static void main(String[] args) {
        MaximumAverageSubarrayI obj = new MaximumAverageSubarrayI();
        int[] nums = {-1, };
        int k = 1;
        System.out.println(
            obj.findMaxAverage(nums, k)
        );
    }
    public double findMaxAverage(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return 0;
        }
        return findMaxAverageSlidingWindow(nums, k);
    }
    private double findMaxAverageSlidingWindow(int[] nums, int k) {
        double maximumAverage = findAverage(nums, 0, k - 1);
        for (int i = 0; i <= nums.length - k; i++) {
            maximumAverage = Math.max(
                maximumAverage,
                findAverage(nums, i, i + k - 1)
            );
        }
        return maximumAverage;
    }
    private double findAverage(int[] nums, int left, int right) {
        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum += nums[i];
        }
        return (sum * 1.0) / (right - left + 1);
    }
}
/* EOF */