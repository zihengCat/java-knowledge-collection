package io.ziheng.array.leetcode;

import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * LeetCode 912. Sort an Array
 * https://leetcode.com/problems/sort-an-array/
 */
public class SortAnArray {
    public static void main(String[] args) {
        int[] arr = new int[]{5,2,3,1 };
        List<Integer> list = new SortAnArray().sortArray(arr);
        System.out.println(list);
        System.out.println(Arrays.toString(arr));
    }
    public List<Integer> sortArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new LinkedList<Integer>();
        }
        List<Integer> resultList = new LinkedList<>();
        //quickSort(nums, 0, nums.length - 1);
        mergeSort(nums, 0, nums.length - 1);
        for (int i = 0; i < nums.length; i++) {
            resultList.add(nums[i]);
        }
        return resultList;
    }
    private void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (right + left) >> 1;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }
    private void merge(int[] arr, int left, int mid, int right) {
        int[] arrCopy = new int[right - left + 1];
        for (int i = left; i < right; i++) {
            arrCopy[i - left] = arr[i];
        }
        int pA = left, pB = mid + 1;
        for (int i = left; i <= right; i++) {
            if (pA > mid) {
                arrCopy[i - left] = arr[pB];
                pB++;
            }
            else if (pB > right) {
                arrCopy[i - left] = arr[pA];
                pA++;
            }
            else if (arr[pA] > arr[pB]) {
                arrCopy[i - left] = arr[pB];
                pB++;
            }
            else if (arr[pA] <= arr[pB]) {
                arrCopy[i - left] = arr[pA];
                pA++;
            }
        }
        for (int i = left; i <= right; i++) {
            arr[i] = arrCopy[i - left];
        }
    }
    private void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int index = partition(arr, left, right);
        quickSort(arr, left, index - 1);
        quickSort(arr, index + 1, right);
    }
    private int partition(int[] arr, int left, int right) {
        int pivot = arr[left];
        int pLeft, pRight;
        for (pLeft = left, pRight = right; pLeft < pRight; /* ... */) {
            while (arr[pRight] > pivot && pLeft < pRight) {
                pRight--;
            }
            while (arr[pLeft] <= pivot && pLeft < pRight) {
                pLeft++;
            }
            if (pLeft < pRight) {
                swap(arr, pLeft, pRight);
            }
        }
        swap(arr, left, pLeft);
        return pLeft;
    }
    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
/* EOF */