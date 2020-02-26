package io.ziheng.sort;

import io.ziheng.sort.Sort;

public class InsertionSort implements Sort {
    @Override
    public void sort(int[] arr) {
        insertionSort(arr);
    }
    /**
     * 插入排序法。
     *
     * 算法思路：
     * 从第二个元素开始，
     * 将待排元素插入到先前已排好序的有序数组中，
     * 迭代循环。
     *
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     *
     * @param arr
     * @return void
     */
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        insertionSort0(arr, 0, arr.length - 1);
    }
    private static void insertionSort0(int[] arr, int left, int right) {
        // 第一个元素已排好序
        for (int i = left + 1; i <= right; i++) {
            for (int j = i; j > left; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                } else {
                    // 重要性质：可提前终止内层循环
                    break;
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