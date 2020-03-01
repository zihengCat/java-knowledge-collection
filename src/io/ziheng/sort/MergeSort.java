package io.ziheng.sort;

import io.ziheng.sort.Sort;

public class MergeSort implements Sort {
    @Override
    public void sort(int[] arr) {
        mergeSort(arr);
    }
    /**
     * 归并排序法。
     * 
     * 算法思路：
     * 将数组分为左右两部分，
     * 对两部分分别排序，合并排序后的数组分区，递归进行。
     *
     * 时间复杂度：O(n * log(n))
     * 空间复杂度：O(n)
     *
     * @param arr
     * @return void
     */
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        mergeSort0(arr, 0, arr.length - 1);
    }
    private static void mergeSort0(int[] arr, int left, int right) {
        // 递归到底：当前仅有1个元素
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort0(arr, left, mid);
        mergeSort0(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }
    /**
     * 合并 arr[left, mid] 与 arr[mid + 1, right] 。
     *
     * @param arr
     * @param left
     * @param mid
     * @param right
     * @return void
     */
    private static void merge(int[] arr, int left, int mid, int right) {
        // 拷贝数组分区
        int[] arrCopy = new int[right - left + 1];
        for (int i = left; i <= right; i++) {
            arrCopy[i - left] = arr[i];
        }
        // 双指针指向已排序分区
        int pA = left;
        int pB = mid + 1;
        // 合并分区
        for (int p = left; p <= right; p++) {
            if (pA > mid) {
                arr[p] = arrCopy[pB - left];
                pB++;
            }
            else if (pB > right) {
                arr[p] = arrCopy[pA - left];
                pA++;
            }
            else if (arrCopy[pA - left] <= arrCopy[pB - left]) {
                arr[p] = arrCopy[pA - left];
                pA++;
            }
            else if (arrCopy[pA - left] > arrCopy[pB - left]) {
                arr[p] = arrCopy[pB - left];
                pB++;
            }
        }
    }
}
/* EOF */