package io.ziheng.sort;

import io.ziheng.sort.Sort;

public class BubbleSort implements Sort {
    @Override
    public void sort(int[] arr) {
        bubbleSort(arr);
    }
    /**
     * 冒泡排序法。
     *
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     *
     * @param arr
     * @return void
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        bubbleSort0(arr, 0, arr.length - 1);
    }
    private static void bubbleSort0(int[] arr, int left, int right) {
        for (int i = left; i <= right; i++) {
            for (int j = left; j <= right; j++) {
                if (arr[j] > arr[i]) {
                    swap(arr, i, j);
                }
            }
        }
    }
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
/* EOF */