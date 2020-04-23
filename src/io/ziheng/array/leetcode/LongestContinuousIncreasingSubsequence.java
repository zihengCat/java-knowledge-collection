package io.ziheng.array.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * LeetCode 674. Longest Continuous Increasing Subsequence
 * https://leetcode.com/problems/longest-continuous-increasing-subsequence/
 */
public class LongestContinuousIncreasingSubsequence {
    public int findLengthOfLCIS(int[] nums) {
        int longestLength = 0;
        Deque<Integer> slidingWindow = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            slidingWindow.offerLast(nums[i]);
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > nums[j - 1]) {
                    slidingWindow.offerLast(nums[j]);
                } else {
                    break;
                }
            }
            longestLength = Math.max(longestLength, slidingWindow.size());
            slidingWindow.clear();
        }
        return longestLength;
    }
}
/* EOF */