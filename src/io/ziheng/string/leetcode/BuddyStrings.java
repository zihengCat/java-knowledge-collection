package io.ziheng.string.leetcode;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

/**
 * LeetCode 859. Buddy Strings
 * https://leetcode.com/problems/buddy-strings/
 */
public class BuddyStrings {
    public static void main(String[] args) {
        BuddyStrings obj = new BuddyStrings();
        String s1 = "ab";
        String s2 = "ba";
        System.out.println(
            obj.buddyStrings(s1, s2)
        );
    }
    /**
     * LeetCode 859. Buddy Strings
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param s1
     * @param s2
     * @return boolean
     */
    public boolean buddyStrings(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false;
        }
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            Set<Character> aSet = new HashSet<>();
            for (char c : s1.toCharArray()) {
                aSet.add(c);
            }
            return aSet.size() < s1.length();
        }
        char[] arrA = s1.toCharArray();
        char[] arrB = s2.toCharArray();
        List<Integer> diffIdx = new ArrayList<>();
        for (int i = 0; i < arrA.length; i++) {
            if (arrA[i] != arrB[i]) {
                diffIdx.add(i);
            }
        }
        if (diffIdx.size() != 2) {
            return false;
        }
        return arrA[diffIdx.get(0)] == arrB[diffIdx.get(1)]
            && arrA[diffIdx.get(1)] == arrB[diffIdx.get(0)];
    }
}

/* EOF */
