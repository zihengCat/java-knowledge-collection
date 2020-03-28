package io.ziheng.bitmanipulation.leetcode;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * LeetCode 1356. Sort Integers by The Number of 1 Bits
 * https://leetcode.com/problems/sort-integers-by-the-number-of-1-bits/
 */
public class SortIntegersByTheNumberOf1Bits {
    /**
     * 主函数 -> 测试用例
     */
    public static void main(String[] args) {
        SortIntegersByTheNumberOf1Bits obj = new SortIntegersByTheNumberOf1Bits();
        int[] arr = new int[] {
            1024,512,256,128,64,32,16,8,4,1,2,
        };
        System.out.println(
            Arrays.toString(
                obj.sortByBits(arr)
            )
        );
        arr = new int[] {
            0,1,2,3,4,5,6,7,8
        };
        System.out.println(
            Arrays.toString(
                obj.sortByBits(arr)
            )
        );
    }
    public int[] sortByBits(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        List<Integer> list = new LinkedList<>();
        for (int n : arr) {
            list.add(n);
        }
        Collections.sort(list, (a, b) -> compareNums(a, b));
        return list.stream().mapToInt(i -> i.intValue()).toArray();
    }
    private int compareNums(int a, int b) {
        int aBits = countBits(a);
        int bBits = countBits(b);
        return aBits == bBits ? a - b : aBits - bBits;
    }
    private int countBits(int n) {
        int cnt = 0;
        while (n > 0) {
            if (n % 2 == 1) {
                cnt++;
            }
            n /= 2;
        }
        return cnt;
    }
}
/* EOF */