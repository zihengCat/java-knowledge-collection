package io.ziheng.string.leetcode;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * LeetCode 1417. Reformat The String
 * https://leetcode.com/problems/reformat-the-string/
 */
public class ReformatTheString {
    public static void main(String[] args) {
        ReformatTheString obj = new ReformatTheString();
        String s = "covid2019";
        System.out.println(
            obj.reformat(s)
        );
        s = "leetcode";
        System.out.println(
            obj.reformat(s)
        );
        s = "123456abc";
        System.out.println(
            obj.reformat(s)
        );
        s = "ab123";
        System.out.println(
            obj.reformat(s)
        );
    }

    private static final String EMPTY_STRING = "";
    private static final int LETTER_FIRST = 0;
    private static final int DIGIT_FIRST = 1;
    public String reformat(String s) {
        if (s == null || s.length() == 0) {
            return EMPTY_STRING;
        }
        PriorityQueue<Character> lettersQueue = new PriorityQueue<>();
        PriorityQueue<Character> digitsQueue = new PriorityQueue<>();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                digitsQueue.offer(c);
            } else {
                lettersQueue.offer(c);
            }
        }
        String letterFirstPermutation = doPermutation(
            LETTER_FIRST,
            new LinkedList<>(lettersQueue),
            new LinkedList<>(digitsQueue)
        );
        return letterFirstPermutation.equals(EMPTY_STRING)
            ? doPermutation(DIGIT_FIRST, lettersQueue, digitsQueue)
            : letterFirstPermutation;
    }
    private String doPermutation(
        int aFlag,
        Queue<Character> lettersQueue,
        Queue<Character> digitsQueue) {
        StringBuilder stringBuilder = new StringBuilder();
        while (!lettersQueue.isEmpty()
            || !digitsQueue.isEmpty()) {
            if (aFlag == 0) {
                if (!lettersQueue.isEmpty()) {
                    stringBuilder.append(lettersQueue.poll());
                } else {
                    break;
                }
            } else if (aFlag == 1) {
                if (!digitsQueue.isEmpty()) {
                    stringBuilder.append(digitsQueue.poll());
                } else {
                    break;
                }
            }
            aFlag = (aFlag == LETTER_FIRST) ? DIGIT_FIRST : LETTER_FIRST;
        }
        if (!lettersQueue.isEmpty()
            || !digitsQueue.isEmpty()) {
            return EMPTY_STRING;
        }
        return stringBuilder.toString();
    }
}
/* EOF */