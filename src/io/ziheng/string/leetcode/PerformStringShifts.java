package io.ziheng.string.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * LeetCode ???. Perform String Shifts
 * https://leetcode.com/
 */
public class PerformStringShifts {
    public static void main(String[] args) {
        PerformStringShifts obj = new PerformStringShifts();
        String s = "abcdefg";
        int[][] shift = {
            {1, 1, },
            {1, 1, },
            {0, 2, },
            {1, 3, },
        };
        System.out.println(
            obj.stringShift(s, shift)
        );
    }
    public String stringShift(String s, int[][] shift) {
        if (s == null || s.length() == 0
            || shift == null || shift.length == 0) {
            return "";
        }
        Deque<Character> deque = new LinkedList<>();
        for (char c : s.toCharArray()) {
            deque.offerLast(c);
        }
        for (int[] operator : shift) {
            int direction = operator[0];
            int amount = operator[1];
            // 0 -> Shift Left
            // 1 -> Shift Right
            if (direction == 0) {
                for (int i = 0; i < amount; i++) {
                    deque.offerLast(deque.pollFirst());
                }
            } else if (direction == 1) {
                for (int i = 0; i < amount; i++) {
                    deque.offerFirst(deque.pollLast());
                }
            }
        }
        return dequeToString(deque);
    }
    private String dequeToString(Deque<Character> deque) {
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(deque.pollFirst());
        }
        return sb.toString();
    }
}
/* EOF */