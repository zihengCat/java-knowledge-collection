package io.ziheng.array.leetcode;
/**
 * LeetCode 162. Find Peak Element
 * https://leetcode.com/problems/find-peak-element/
 */
public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        for (int i = 0; i < nums.length; i++) {
            if (isPeakElement(i, nums)) {
                return i;
            }
        }
        return -1;
    }
    private boolean isPeakElement(int index, int[] arr) {
        int y = arr[index];
        int x = index - 1 < 0 ? Integer.MIN_VALUE : arr[index - 1];
        int z = index + 1 < arr.length ? arr[index + 1] : Integer.MIN_VALUE;
        if (x == Integer.MIN_VALUE && z == Integer.MIN_VALUE) {
            return true;
        }
        if (x == Integer.MIN_VALUE) {
            return y > z;
        } else if (z == Integer.MIN_VALUE) {
            return y > x;
        } else {
            return y > x && y > z;
        }
    }
}
/* EOF */