package io.ziheng.string.leetcode;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

/**
 * LeetCode 824. Goat Latin
 * https://leetcode.com/problems/goat-latin/
 */
public class GoatLatin {
    public static void main(String[] args) {
        // ...
    }
    private Set<Character> vowelsSet = new HashSet<>(Arrays.asList(
        'a', 'e', 'i', 'o', 'u',
        'A', 'E', 'I', 'O', 'U'
    ));
    public String toGoatLatin(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        String[] arr = s.split(" ");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = isVowel(arr[i].charAt(0))
                ? arr[i] + "ma"
                : (arr[i] + arr[i].charAt(0) + "ma").substring(1);
            arr[i] += generateCharacter('a', i + 1);
        }
        return String.join(" ", arr);
    }
    private String generateCharacter(char c, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(c);
        }
        return sb.toString();
    }
    private boolean isVowel(char c) {
        return this.vowelsSet.contains(c);
    }
}
/* EOF */