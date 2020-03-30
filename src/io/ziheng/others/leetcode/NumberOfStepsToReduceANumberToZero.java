package io.ziheng.others.leetcode;
/**
 * LeetCode 1342. Number of Steps to Reduce a Number to Zero
 * https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero/
 */
public class NumberOfStepsToReduceANumberToZero {
    public int numberOfSteps (int num) {
        if (num < 0) {
            return 0;
        }
        int cnt = 0;
        int currentNum = num;
        while (currentNum != 0) {
            cnt++;
            if (currentNum % 2 == 0) {
                currentNum /= 2;
            } else {
                currentNum -= 1;
            }
        }
        return cnt;
    }
}
/* EOF */