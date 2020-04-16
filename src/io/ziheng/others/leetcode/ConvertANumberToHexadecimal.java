package io.ziheng.others.leetcode;
/**
 * LeetCode 405. Convert a Number to Hexadecimal
 * https://leetcode.com/problems/convert-a-number-to-hexadecimal/
 */
public class ConvertANumberToHexadecimal {
    private static char[] hexArray = {
        '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f',
    };
    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        String resultString = "";
        while (num != 0) {
            resultString = hexArray[num & 15] + resultString;
            num >>>= 4;
        }
        return resultString;
    }
}
/* EOF */