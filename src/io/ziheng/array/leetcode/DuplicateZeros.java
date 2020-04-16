package io.ziheng.array.leetcode;

import java.util.List;
import java.util.ArrayList;

/**
 * LeetCode 1089. Duplicate Zeros
 * https://leetcode.com/problems/duplicate-zeros/
 */
public class DuplicateZeros {
    public void duplicateZeros(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int maximumLength = arr.length;
        int currentLength = 0;
        List<Integer> aList = new ArrayList<>();
        for (int n : arr) {
            if (n == 0) {
                aList.add(0);
                aList.add(0);
                currentLength += 2;
            } else {
                aList.add(n);
            }
            if (currentLength >= maximumLength) {
                break;
            }
        }
        for (int i = 0; i < maximumLength; i++) {
            arr[i] = aList.get(i);
        }
    }
}
/* EOF */