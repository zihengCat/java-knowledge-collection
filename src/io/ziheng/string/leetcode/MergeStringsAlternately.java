package io.ziheng.string.leetcode;
/**
 * LeetCode 1768. Merge Strings Alternately
 * https://leetcode.com/problems/merge-strings-alternately/
 */
public class MergeStringsAlternately {
    public static void main(String[] args) {
        MergeStringsAlternately obj = new MergeStringsAlternately();
        String word1 = "abc";
        String word2 = "pqr";
        System.out.println(
            word1 + " + " + word2 + " -> "
            + obj.mergeAlternately(word1, word2)
        );
        word1 = "ab";
        word2 = "pqrs";
        System.out.println(
            word1 + " + " + word2 + " -> "
            + obj.mergeAlternately(word1, word2)
        );
        word1 = "abcd";
        word2 = "pq";
        System.out.println(
            word1 + " + " + word2 + " -> "
            + obj.mergeAlternately(word1, word2)
        );
    }
    public String mergeAlternately(String word1, String word2) {
        if (word1 == null && word2 == null) {
            return null;
        }
        StringBuilder sBuilder = new StringBuilder();
        char[] w1Arr = word1.toCharArray();
        char[] w2Arr = word2.toCharArray();
        int w1Length = word1.length();
        int w2Length = word2.length();
        int minLength = minInt(w1Length, w2Length);
        int currentRound = 0; // word1
        int idx = 0;
        for (/* ... */; idx < minLength; /* idx++ */) {
            switch (currentRound) {
                case 0:
                    sBuilder.append(w1Arr[idx]);
                    break;
                case 1:
                    sBuilder.append(w2Arr[idx]);
                    idx++; // word2 round
                    break;
                default:
                    break;
            }
            currentRound = flipRound(currentRound);
        }
        if (w1Length > minLength) {
            appendLast(sBuilder, w1Arr, idx);
        }
        if (w2Length > minLength) {
            appendLast(sBuilder, w2Arr, idx);
        }
        return sBuilder.toString();
    }
    private void appendLast(StringBuilder sBuilder, char[] arr, int idx) {
        for (/* ... */; idx < arr.length; idx++) {
            sBuilder.append(arr[idx]);
        }
    }
    private int flipRound(int round) {
        switch (round) {
            case 0:
                return 1;
            case 1:
                return 0;
            default:
                return 0;
        }
    }
    private int minInt(int a, int b) {
        return a < b ? a : b;
    }
}

/* EOF */
