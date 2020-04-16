package io.ziheng.string.leetcode;

import java.util.Set;
import java.util.HashSet;

/**
 * LeetCode 345. Reverse Vowels of a String
 * https://leetcode.com/problems/reverse-vowels-of-a-string/
 */
public class ReverseVowelsOfAString {
    private static Set<Character> aSet = new HashSet<>();
    static {
        char[] vowelsArray = {
            'a', 'e', 'i', 'o', 'u',
            'A', 'E', 'I', 'O', 'U',
        };
        for (char c : vowelsArray) {
            aSet.add(c);
        }
    }
    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        return reverseVowelsTwoPointer(s);
    }
    private String reverseVowelsTwoPointer(String s) {
        char[] arr = s.toCharArray();
        for (int pLeft = 0, pRight = arr.length - 1;
            pLeft < pRight;
            pLeft++, pRight--) {
            while (pLeft < pRight && !aSet.contains(arr[pLeft])) {
                pLeft++;
            }
            while (pLeft < pRight && !aSet.contains(arr[pRight])) {
                pRight--;
            }
            if (pLeft < pRight) {
                swap(arr, pLeft, pRight);
            }
        }
        return String.valueOf(arr);
    }
    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
/* EOF */