package io.ziheng.recursion.backtracking.leetcode;

import java.util.Comparator;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * LeetCode 949. Largest Time for Given Digits
 * https://leetcode.com/problems/largest-time-for-given-digits/
 */
public class LargestTimeForGivenDigits {
    public static void main(String[] args) {
        LargestTimeForGivenDigits obj = new LargestTimeForGivenDigits();
        int[] arr = new int[]{
            1, 2, 3, 4,
        };
        System.out.println(obj.largestTimeFromDigits(arr));
    }
    public String largestTimeFromDigits(int[] arr) {
        if (arr == null || arr.length != 4) {
            return "";
        }
        List<int[]> aList = new LinkedList<>();
        doPermutations(arr, 0, aList);
        doDecreasingSort(aList);
        for (int[] a : aList) {
            if (a[0] * 10 + a[1] < 24 && a[2] < 6) {
                return String.format("%d%d:%d%d", a[0], a[1], a[2], a[3]);
            }
        }
        return "";
    }
    private void doPermutations(int[] arr, int idx, List<int[]> list) {
        if (idx == arr.length) {
            // Copy array
            int[] a = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                a[i] = arr[i];
            }
            list.add(a);
            return;
        }
        for (int i = idx; i < arr.length; i++) {
            swap(arr, i, idx);
            doPermutations(arr, idx + 1, list);
            swap(arr, i, idx);
        }
    }
    private void doDecreasingSort(List<int[]> list) {
        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] arrA, int[] arrB) {
                for (int i = 0; i < arrA.length; i++) {
                    if (arrA[i] > arrB[i]) {
                        return -1;
                    } else if (arrA[i] < arrB[i]) {
                        return 1;
                    }
                }
                // Equal
                return 0;
            }
        });
    }
    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
/* EOF */