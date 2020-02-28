package io.ziheng.sort;

import io.ziheng.sort.Sort;

public class QuickSort implements Sort {
    @Override
    public void sort(int[] arr) {
        quickSort(arr);
    }
    /**
     * 快速排序法。
     *
     * 算法思路：
     * 取基准点 pivot，将数组分区为小于大于等于基准点的两部分（基准点已好排序），
     * 对基准点左右子数组进行快速排序，递归进行。
     * - 分区算法：
     *     从数组右侧寻找第一个大于基准点的元素，
     *     从数组左侧寻找第一个小于等于基准点的元素，
     *     交换，迭代进行。
     *
     * 时间复杂度：O(n * log(n))
     * 空间复杂度：O(1)
     *
     * @param arr
     * @return void
     */
    public static void quickSort(int[] arr) {
        quickSort0(arr, 0, arr.length - 1);
    }
    private static void quickSortThreeWays(int[] arr, int left, int right) {

    }
    private static void quickSort0(int[] arr, int left, int right) {
        // 递归到底
        if (left >= right) {
            return;
        }
        int i = partition(arr, left, right);
        quickSort0(arr, left, i - 1);
        quickSort0(arr, i + 1, right);
    }
    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[left];
        int pLeft, pRight;
        for (pLeft = left, pRight = right; pRight > pLeft; /* ... */ ) {
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
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
/* EOF */