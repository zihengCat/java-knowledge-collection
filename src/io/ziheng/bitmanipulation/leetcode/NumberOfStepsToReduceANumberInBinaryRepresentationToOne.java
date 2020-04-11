package io.ziheng.bitmanipulation.leetcode;
/**
 * 1404. Number of Steps to Reduce a Number in Binary Representation to One
 * https://leetcode.com/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one/
 */
public class NumberOfStepsToReduceANumberInBinaryRepresentationToOne {
    public static void main(String[] args) {
        NumberOfStepsToReduceANumberInBinaryRepresentationToOne obj =
        new NumberOfStepsToReduceANumberInBinaryRepresentationToOne();
        System.out.println(
            obj.numSteps("1101")
        );
    }
    public int numSteps(String s) {
        return countingSteps(binaryToInt(s));
    }
    private int binaryToInt(String s) {
        /**
         * Binary: 101
         * Integer: 5
         */
        int n = 0;
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            int bit = arr[i] - '0';
            n = n * 2 + bit;
        }
        return n;
    }
    private int countingSteps(int n) {
        int cnt = 0;
        while (n != 1) {
            cnt++;
            if (n % 2 == 0) {
                n /= 2;
            } else {
                n += 1;
            }
        }
        return cnt;
    }
}
/* EOF */