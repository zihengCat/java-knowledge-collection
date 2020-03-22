package io.ziheng.array.leetcode;
/**
 * LeetCode 75. Sort Colors
 * https://leetcode.com/problems/sort-colors/
 */
public class SortColors {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        quickSort(nums, 0, nums.length - 1);
    }
    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int index = partition(nums, left, right);
        quickSort(nums, left, index - 1);
        quickSort(nums, index + 1, right);
    }
    private int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        int pLeft = left;
        int pRight = right;
        while (pLeft < pRight) {
            while (nums[pRight] > pivot && pLeft < pRight) {
                pRight--;
            }
            while (nums[pLeft] <= pivot && pLeft < pRight) {
                pLeft++;
            }
            if (pLeft < pRight) {
                swap(nums, pLeft, pRight);
            }
        }
        swap(nums, left, pLeft);
        return pLeft;
    }
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
/* EOF */