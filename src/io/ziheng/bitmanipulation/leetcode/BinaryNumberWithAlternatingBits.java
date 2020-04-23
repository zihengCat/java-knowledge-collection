package io.ziheng.bitmanipulation.leetcode;
/**
 * LeetCode 693. Binary Number with Alternating Bits
 * https://leetcode.com/problems/binary-number-with-alternating-bits/
 */
public class BinaryNumberWithAlternatingBits {
    public boolean hasAlternatingBits(int n) {
        char[] bits = Integer.toBinaryString(n).toCharArray();
        for (int i = 1; i < bits.length; i++) {
            if (bits[i] == bits[i - 1]) {
                return false;
            }
        }
        return true;
    }
}
/* EOF */