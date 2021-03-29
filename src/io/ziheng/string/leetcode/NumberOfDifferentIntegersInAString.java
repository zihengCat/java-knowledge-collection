package io.ziheng.string.leetcode;

import java.util.Set;
import java.util.HashSet;

/**
 * LeetCode 1805. Number of Different Integers in a String
 * https://leetcode.com/problems/number-of-different-integers-in-a-string/
 */
class NumberOfDifferentIntegersInAString {
    public static void main(String[] args) {
        // ...
    }
    public int numDifferentIntegers(String word) {
        if (word == null || word.length() == 0) {
            return 0;
        }
        Set<String> aSet = new HashSet<>();
        for (int i = 0; i < word.length(); i++) {
            if (Character.isDigit(word.charAt(i))) { // Parse number string
                int steps = 0;
                String num = "";
                for (/* ... */;
                    i + steps < word.length()
                    && Character.isDigit(word.charAt(i + steps));
                    steps++
                ) {
                    num += word.charAt(i + steps);
                }
                aSet.add(this.removeLeadingZeros(num));
                i += steps;
            }
        }
        return aSet.size();
    }
    private String removeLeadingZeros(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        StringBuilder sBuilder = new StringBuilder();
        int i = 0;
        while (i < s.length() && s.charAt(i) == '0') {
            i++;
        }
        for (/* ... */; i < s.length(); i++) {
            sBuilder.append(s.charAt(i));
        }
        return sBuilder.toString();
    }
}

/* EOF */
