package io.ziheng.array.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * LeetCode 66. Plus One
 * https://leetcode.com/problems/plus-one/
 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        Deque<Integer> deque = new LinkedList<>();
        int carry = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            int num = 0;
            if (i == digits.length - 1) {
                num = digits[i] + 1 + carry;
            } else {
                num = digits[i] + carry;
            }
            if (num >= 10) {
                carry = num / 10;
                num = num % 10;
            } else {
                carry = 0;
            }
            deque.offerFirst(num);
        }
        if (carry > 0) {
            deque.offerFirst(carry);
        }
        int[] resultArray = new int[deque.size()];
        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = deque.pollFirst();
        }
        return resultArray;
    }
}
/* EOF */