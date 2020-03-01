package io.ziheng.sort;

import io.ziheng.sort.Sort;

public class HeapSort implements Sort {
    @Override
    public void sort(int[] arr) {
        heapSort(arr, 0, arr.length - 1);
    }
    public static void heapSort(int[] arr, int left, int right) {
        for (int heapSize = right - left + 1; heapSize > 0; heapSize--) {
            heapify(arr, left, heapSize);
            swap(arr, 0, heapSize - 1);
        }
    }
    private static void heapify(int[] arr, int index, int heapSize) {
        for (int i = getParent(heapSize - 1); i >= index; i--) {
            siftDown(arr, i, heapSize);
        }
    }
    private static void siftDown(int[] arr, int i, int heapSize) {
        while (getLeft(i) < heapSize) {
            int leftIndex = getLeft(i);
            int rightIndex = getRight(i);
            int maxIndex = leftIndex;
            if (rightIndex < heapSize && arr[rightIndex] > arr[leftIndex]) {
                maxIndex = rightIndex;
            }
            if (arr[i] > arr[maxIndex]) {
                break;
            }
            swap(arr, i, maxIndex);
            i = maxIndex;
        }
    }
    private static int getLeft(int index) {
        return index * 2 + 1;
    }
    private static int getRight(int index) {
        return index * 2 + 2;
    }
    private static int getParent(int index) {
        return (index - 1) / 2;
    }
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
/* EOF */