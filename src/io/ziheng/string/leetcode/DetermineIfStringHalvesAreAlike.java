package io.ziheng.string.leetcode;

import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;

/**
 * 1704. Determine if String Halves Are Alike
 * https://leetcode.com/problems/determine-if-string-halves-are-alike/
 */
public class DetermineIfStringHalvesAreAlike {
    public static void main(String[] args) {
        DetermineIfStringHalvesAreAlike obj = new DetermineIfStringHalvesAreAlike();
        String s = "book";
        System.out.println(s + " -> " + obj.halvesAreAlike(s));
        s = "textbook";
        System.out.println(s + " -> " + obj.halvesAreAlike(s));
        s = "MerryChristmas";
        System.out.println(s + " -> " + obj.halvesAreAlike(s));
        s = "AbCdEfGh";
        System.out.println(s + " -> " + obj.halvesAreAlike(s));
    }
    static Set<Character> vowelsSet = new HashSet<>(Arrays.asList(
        'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'
    ));
    public boolean halvesAreAlike(String s) {
        if (s == null || s == "") {
            return false;
        }
        int cntFirst = 0;
        int cntSecond = 0;
        for (int pFirst = 0, pSecond = s.length() - 1;
            pFirst < pSecond;
            pFirst++, pSecond--
        ) {
            if (vowelsSet.contains(s.charAt(pFirst))) {
                cntFirst++;
            }
            if (vowelsSet.contains(s.charAt(pSecond))) {
                cntSecond++;
            }
        }
        return cntFirst == cntSecond;
    }
}

/* EOF */
