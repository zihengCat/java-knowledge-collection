package io.ziheng.array.leetcode;
public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int breakingPoint = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                breakingPoint = i - 1;
                break;
            }
        }
        int findLeft = binarySearch(nums, 0, breakingPoint, target);
        int findRight = binarySearch(nums, breakingPoint + 1, nums.length - 1, target);
        return findLeft != -1 ? findLeft
        : findRight != -1 ? findRight : -1;
    }
    private int binarySearch(int[] nums, int left, int right, int target) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target) {
            return binarySearch(nums, left, mid - 1, target);
        } else if (nums[mid] < target) {
            return binarySearch(nums, mid + 1, right, target);
        }
        return -1;
    }
}
/* EOF */