package io.ziheng.bitmanipulation.leetcode;
/**
 * LeetCode 191. Number of 1 Bits
 * https://leetcode.com/problems/number-of-1-bits/
 */
public class NumberOfOneBits {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        // return Integer.bitCount(n);
        int cnt = 0;
        int mask = 0x01;
        for (int i = 0; i < 32; i++) {
            cnt += n & mask;
            n >>>= 1;
        }
        return cnt;
    }
}
/* EOF */