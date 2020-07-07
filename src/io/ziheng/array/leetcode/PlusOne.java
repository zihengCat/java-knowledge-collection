package io.ziheng.array.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * LeetCode 66. Plus One
 * https://leetcode.com/problems/plus-one/
 */
public class PlusOne {
    public int[] plusOneT(int[] digits) {
        if (digits == null || digits.length == 0) {
            return null;
        }
        Deque<Integer> aDeque = new LinkedList<>();
        int carryNum = 0;
        int oneNum = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int num = digits[i];
            if (i == digits.length - 1) {
                num = digits[i] + carryNum + oneNum;
            } else {
                num = digits[i] + carryNum;
            }
            if (num >= 10) {
                carryNum = num / 10;
                num = num % 10;
            } else {
                carryNum = 0;
            }
            aDeque.offerFirst(num);
        }
        if (carryNum > 0) {
            aDeque.offerFirst(carryNum);
        }
        int size = aDeque.size();
        int[] rArray = new int[size];
        for (int i = 0; i < size; i++) {
            rArray[i] = aDeque.pollFirst();
        }
        return rArray;
    }
}
/* EOF */