package io.ziheng.array.leetcode;
/**
 * LeetCode 34. Find First and Last Position of Element in Sorted Array
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class FindFirstAndLastPositionOfElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int[] resultArray = new int[]{-1, -1};
        for (int pLeft = 0; pLeft < nums.length; pLeft++) {
            if (nums[pLeft] == target) {
                resultArray[0] = pLeft;
                break;
            }
        }
        // 快速返回 -> 目标不存在
        if (resultArray[0] == -1) {
            return resultArray;
        }
        for (int pRight = nums.length - 1; pRight >= 0; pRight--) {
            if (nums[pRight] == target) {
                resultArray[1] = pRight;
                break;
            }
        }
        return resultArray;
    }
}
/* EOF */