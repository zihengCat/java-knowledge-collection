package io.ziheng.array.leetcode;
/**
 * LeetCode 88. Merge Sorted Array
 * https://leetcode.com/problems/merge-sorted-array/
 */
public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int pA = 0;
        int pB = 0;
        int pIndex = 0;
        int[] arr = new int[m];
        while (pA < m && pB < n) {
            if (nums1[pA] < nums2[pB]) {
                arr[pIndex] = nums1[pA];
                pA++;
            } else {
                arr[pIndex] = nums2[pB];
                pB++;
            }
            pIndex++;
        }
        while (pA < m) {
            arr[pIndex] = nums1[pA];
            pA++;
            pIndex++;
        }
        while (pB < n) {
            arr[pIndex] = nums2[pB];
            pB++;
            pIndex++;
        }
        for (int i = 0; i < arr.length; i++) {
            nums1[i] = arr[i];
        }
    }
}
/* EOF */