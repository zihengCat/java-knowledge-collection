package io.ziheng.array.leetcode;
/**
 * LeetCode 4. Median of Two Sorted Arrays
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 */
public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        MedianOfTwoSortedArrays obj = new MedianOfTwoSortedArrays();
        int[] nums1 = {1, 2, };
        int[] nums2 = {3, 4, };
        System.out.println(
            obj.findMedianSortedArrays(nums1, nums2)
        );
    }
    /**
     * Time Complexity: O(n + m)
     * Space Complexity: O(n + m)
     *
     * @param nums1
     * @param nums2
     * @return double
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums = mergeSortedArray(nums1, nums2);
        if (nums.length % 2 == 0) {
            return ((double)(
                nums[nums.length / 2]
                + nums[nums.length / 2 - 1]
            )) / 2;
        } else {
            return (double)(nums[nums.length / 2]);
        }
    }
    private int[] mergeSortedArray(int[] sortedArrayA, int[] sortedArrayB) {
        int[] resultArray = new int[sortedArrayA.length + sortedArrayB.length];
        int pA = 0;
        int pB = 0;
        int index = 0;
        while (pA < sortedArrayA.length && pB < sortedArrayB.length) {
            if (sortedArrayA[pA] < sortedArrayB[pB]) {
                resultArray[index] = sortedArrayA[pA];
                pA++;
                index++;
            } else {
                resultArray[index] = sortedArrayB[pB];
                pB++;
                index++;
            }
        }
        while (pA < sortedArrayA.length) {
            resultArray[index] = sortedArrayA[pA];
            pA++;
            index++;
        }
        while (pB < sortedArrayB.length) {
            resultArray[index] = sortedArrayB[pB];
            pB++;
            index++;
        }
        return resultArray;
    }
}
/* EOF */