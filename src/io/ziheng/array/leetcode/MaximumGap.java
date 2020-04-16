package io.ziheng.array.leetcode;

import java.util.Arrays;

/**
 * LeetCode 164. Maximum Gap
 * https://leetcode.com/problems/maximum-gap/
 */
public class MaximumGap {
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] sortedNums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sortedNums);
        int maximumGap = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (inSorted(nums[i], nums[i + 1], sortedNums)) {
                maximumGap = Math.max(maximumGap, Math.abs(nums[i] - nums[i + 1]));
            }
        }
        return maximumGap;
    }
    private boolean inSorted(int x, int y, int[] sortedNums) {
        int a = Math.min(x, y);
        int b = Math.max(x, y);
        for (int i = 0; i < sortedNums.length; i++) {
            if (sortedNums[i] == a) {
                for (int j = i + 1; j < sortedNums.length; j++) {
                    if (sortedNums[j] == b) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
/* EOF */