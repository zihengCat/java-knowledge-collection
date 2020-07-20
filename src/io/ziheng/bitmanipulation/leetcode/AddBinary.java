package io.ziheng.bitmanipulation.leetcode;
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
    public String addBinarySimple(String a, String b) {
        if (a == null || b == null) {
            return a == null ? b : a;
        }
        int aInt = Integer.parseInt(a, 2);
        int bInt = Integer.parseInt(b, 2);
        return Integer.toBinaryString(aInt + bInt);
    }
    public String addBinary(String a, String b) {
        if (a == null || b == null) {
            return a == null ? b : a;
        }
        int aIndex = a.length() - 1;
        int bIndex = b.length() - 1;
        int carryBit = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (aIndex >= 0 || bIndex >= 0) {
            int aInt = aIndex >= 0 ? charToInt(a.charAt(aIndex)) : 0;
            int bInt = bIndex >= 0 ? charToInt(b.charAt(bIndex)) : 0;
            int sumNum = aInt + bInt + carryBit;
            if (sumNum == 0) {
                stringBuilder.insert(0, '0');
                carryBit = 0;
            } else if (sumNum == 1) {
                stringBuilder.insert(0, '1');
                carryBit = 0;
            } else if (sumNum == 2) {
                stringBuilder.insert(0, '0');
                carryBit = 1;
            } else if (sumNum == 3) {
                stringBuilder.insert(0, '1');
                carryBit = 1;
            }
            aIndex--;
            bIndex--;
        }
        if (carryBit > 0) {
            stringBuilder.insert(0, '1');
        }
        return stringBuilder.toString();
    }
    private int charToInt(char c) {
        return c - '0';
    }
}
/* EOF */