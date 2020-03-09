package io.ziheng.list.leetcode;

import io.ziheng.list.leetcode.ListNode;

/**
 * LeetCode 1290. Convert Binary Number in a Linked List to Integer
 * https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/
 */
public class ConvertBinaryNumberInALinkedListToInteger {
    public int getDecimalValue(ListNode head) {
        int ans = 0;
        while (head != null) {
            // ans = ans * 2 + head.val;
            ans = (ans << 1) | head.val;
            head = head.next;
        }
        return ans;
    }
}
/* EOF */