package io.ziheng.array.leetcode;
/**
 * LeetCode 209. Minimum Size Subarray Sum
 * https://leetcode.com/problems/minimum-size-subarray-sum/
 */
public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int windowStart = 0;
        int windowEnd = 0;
        int windowSum = 0;
        int windowLength = Integer.MAX_VALUE;
        int n = nums.length;
        for (windowEnd = 0; windowEnd < n; windowEnd++) {
            windowSum += nums[windowEnd];
            while (windowSum >= s) {
                windowLength = Math.min(windowLength, windowEnd - windowStart + 1);
                windowSum -= nums[windowStart];
                windowStart++;
            }
        }
        return windowLength == Integer.MAX_VALUE ? 0 : windowLength;
    }
}
/* EOF */