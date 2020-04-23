package io.ziheng.bitmanipulation.leetcode;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

/**
 * LeetCode 762. Prime Number of Set Bits in Binary Representation
 * https://leetcode.com/problems/prime-number-of-set-bits-in-binary-representation/
 */
public class PrimeNumberOfSetBitsInBinaryRepresentation {
    public int countPrimeSetBits(int L, int R) {
        Set<Integer> primesSet = new HashSet<>(
            Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29)
        );
        int cnt = 0;
        for (int num = L; num <= R; num++) {
            if (primesSet.contains(Integer.bitCount(num))) {
                cnt++;
            }
        }
        return cnt;
    }
}
/* EOF */