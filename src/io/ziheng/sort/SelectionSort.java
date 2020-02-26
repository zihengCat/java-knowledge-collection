package io.ziheng.sort;

import io.ziheng.sort.Sort;

public class SelectionSort implements Sort {
    @Override
    public void sort(int[] arr) {
        selectionSort(arr);
    }
    /**
     * 选择排序法。
     *
     * 算法思路：
     * 寻找数组中最大/最小的元素，
     * 与队头元素交换，迭代循环。
     *
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     *
     * @param arr
     * @return void
     */
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        selectionSort0(arr, 0, arr.length - 1);
    }
    private static void selectionSort0(int[] arr, int left, int right) {
        for (int i = left; i <= right; i++) {
            // 从小到大
            int minimumIndex = findMinimum(arr, i, right);
            swap(arr, i, minimumIndex);
            // 从大到小
            //int maximumIndex = findMaximum(arr, i, right);
            //swap(arr, i, maximumIndex);
        }
    }
    private static int findMaximum(int[] arr, int left, int right) {
        int maximumIndex = left;
        for (int i = left; i <= right; i++) {
            if (arr[i] > arr[maximumIndex]) {
                maximumIndex = i;
            }
        }
        return maximumIndex;
    }
    private static int findMinimum(int[] arr, int left, int right) {
        int minimumIndex = left;
        for (int i = left; i <= right; i++) {
            if (arr[i] < arr[minimumIndex]) {
                minimumIndex = i;
            }
        }
        return minimumIndex;
    }
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
/* EOF */