package io.ziheng.bitmanipulation.leetcode;

import java.math.BigInteger;

/**
 * LeetCode 67. Add Binary
 * https://leetcode.com/problems/add-binary/
 */
public class AddBinary {
    public static void main(String[] args) {
        System.out.println('1' - '0');
        AddBinary addBinary = new AddBinary();
        System.out.println(
            addBinary.addBinary("11", "11")
        );
    }
    public String addBinary(String a, String b) {
        if (a == null || b == null) {
            return a == null ? b : a;
        }
        int aLength = a.length() - 1;
        int bLength = b.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (aLength >= 0 || bLength >= 0) {
            int aInt = aLength >= 0 ? charToInt(a.charAt(aLength)) : 0;
            int bInt = bLength >= 0 ? charToInt(b.charAt(bLength)) : 0;
            if (aInt + bInt + carry == 0) {
                sb.append('0');
                carry = 0;
            } else if (aInt + bInt + carry == 1) {
                sb.append('1');
                carry = 0;
            } else if (aInt + bInt + carry == 2) {
                sb.append('0');
                carry = 1;
            } else if (aInt + bInt + carry == 3) {
                sb.append('1');
                carry = 1;
            }
            aLength--;
            bLength--;
        }
        if (carry > 0) {
            sb.append('1');
        }
        return sb.reverse().toString();
    }
    private int charToInt(char c) {
        return c - '0';
    }
}
/* EOF */