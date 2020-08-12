package io.ziheng.sort.leetcode;
/**
 * LeetCode 274. H-Index
 * https://leetcode.com/problems/h-index/
 */
public class HIndex {
    public static void main(String[] args) {
        int[] arr = {3,0,6,1,5,};
        HIndex obj = new HIndex();
        System.out.println(obj.hIndex(arr));
        // ...
    }
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        int hIndex = 0;
        quickSort(citations, 0, citations.length - 1);
        for (int i = 0; i < citations.length; i++) {
            if (citations[i] > i) {
                hIndex++;
            }
        }
        return hIndex;
    }
    private static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int i = partition(arr, left, right);
        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }
    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[left];
        int pLeft, pRight;
        for (pLeft = left, pRight = right; pLeft < pRight; /* ... */) {
            while (arr[pRight] < pivot && pLeft < pRight) {
                pRight--;
            }
            while (arr[pLeft] >= pivot && pLeft < pRight) {
                pLeft++;
            }
            swap(arr, pLeft, pRight);
        }
        swap(arr, left, pLeft);
        return pLeft;
    }
    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
/* EOF */