package io.ziheng.array.leetcode;

import java.util.List;
import java.util.LinkedList;

/**
 * LeetCode 989. Add to Array-Form of Integer
 * https://leetcode.com/problems/add-to-array-form-of-integer/
 */
public class AddToArrayFormOfInteger {
    public static void main(String[] args) {
        AddToArrayFormOfInteger test = new AddToArrayFormOfInteger();
        System.out.println(
            test.addToArrayForm(
                new int[]{9,9,9,9,9,9,9,9,9,9, },
                1
            )
        );
    }
    public List<Integer> addToArrayForm(int[] A, int K) {
        if (A == null || A.length == 0) {
            return new LinkedList<>();
        }
        LinkedList<Integer> resultList = new LinkedList<>();
        for (int i = A.length - 1; i >= 0; i--) {
            resultList.addFirst(
                (A[i] + K) % 10
            );
            K = (A[i] + K) / 10;
        }
        while (K > 0) {
            resultList.addFirst(K % 10);
            K /= 10;
        }
        return resultList;
    }
}
/* EOF */