package io.ziheng.bitmanipulation.leetcode;

import java.util.Arrays;

/**
 * LeetCode 338. Counting Bits
 * https://leetcode.com/problems/counting-bits/
 */
public class CountingBits {
    // 主函数 -> 测试用例
    public static void main(String[] args) {
        CountingBits obj = new CountingBits();
        System.out.println(
            Arrays.toString(
                obj.countBits(2)
            )
        );
        System.out.println(
            Arrays.toString(
                obj.countBits(5)
            )
        );
    }
    /**
     * 统计比特位 1 的数目
     *
     * 时间复杂度：O(n * sizeof(m))
     * 空间辅助度：O(n)
     *
     * 1. 开结果数组 -> 容量 n + 1
     * 2. 依次计算 [0... n] 数值的比特位 1 数目
     * 3. 二进制计算方法 -> 对 2 取余
     *
     * @param num
     * @return int[]
     */
    public int[] countBits(int num) {
        // 异常情况
        if (num < 0) {
            return new int[0];
        }
        int[] resultArray = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            resultArray[i] = countNumBits(i);
        }
        return resultArray;
    }
    private int countNumBits(int num) {
        int cnt = 0;
        while (num > 0) {
            int remainder = num % 2;
            num /= 2;
            if (remainder == 1) {
                cnt++;
            }
        }
        return cnt;
    }
}
/* EOF */