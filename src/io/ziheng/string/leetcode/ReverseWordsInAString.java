package io.ziheng.string.leetcode;
/**
 * LeetCode 151. Reverse Words in a String
 * https://leetcode.com/problems/reverse-words-in-a-string/
 */
public class ReverseWordsInAString {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        s = s.trim();
        String[] arr = s.split("\s+");
        for (int pLeft = 0, pRight = arr.length - 1;
            pLeft < pRight;
            pLeft++, pRight--) {
            swap(arr, pLeft, pRight);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            stringBuilder.append(arr[i]);
            if (i != arr.length - 1) {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }
    private <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
/* EOF */