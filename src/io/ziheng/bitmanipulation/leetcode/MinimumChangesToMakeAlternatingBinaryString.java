package io.ziheng.bitmanipulation.leetcode;
/**
 * LeetCode 1758. Minimum Changes To Make Alternating Binary String
 * https://leetcode.com/problems/minimum-changes-to-make-alternating-binary-string/
 */
public class MinimumChangesToMakeAlternatingBinaryString {
    public static void main(String[] args) {
        MinimumChangesToMakeAlternatingBinaryString obj = new MinimumChangesToMakeAlternatingBinaryString();
        String s = "0100";
        System.out.println(s + " -> " + obj.minOperations(s));
        s = "1111";
        System.out.println(s + " -> " + obj.minOperations(s));
        s = "10";
        System.out.println(s + " -> " + obj.minOperations(s));
    }
    public int minOperations(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] sArr = s.toCharArray();
        char patternA = '1';
        char patternB = '0';
        int cntA = 0;
        int cntB = 0;
        for (int i = 0; i < sArr.length; i++) {
            if (sArr[i] != patternA) {
                cntA++;
            }
            patternA = flipBit(patternA);
        }
        for (int i = 0; i < sArr.length; i++) {
            if (sArr[i] != patternB) {
                cntB++;
            }
            patternB = flipBit(patternB);
        }
        return minInt(cntA, cntB);
    }
    private char flipBit(char b) {
        switch (b) {
            case '0':
                return '1';
            case '1':
                return '0';
            default:
                return '0';
        }
    }
    private int minInt(int a, int b) {
        return a < b ? a : b;
    }
}

/* EOF */
