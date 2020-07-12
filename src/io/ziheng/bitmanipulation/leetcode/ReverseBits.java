package io.ziheng.bitmanipulation.leetcode;
/**
 * LeetCode 190. Reverse Bits
 * https://leetcode.com/problems/reverse-bits/
 */
public class ReverseBits {
    public static void main(String[] args) {
        // ...
    }
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int r = 0x00;
        for (int i = 0; i < 32; i++) {
            r <<= 1;
            r += n & 0x01;
            n >>>= 1;
        }
        return r;
    }
}
/* EOF */