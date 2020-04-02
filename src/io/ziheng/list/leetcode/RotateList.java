package io.ziheng.list.leetcode;

import io.ziheng.list.leetcode.ListNode;

/**
 * LeetCode 61. Rotate List
 * https://leetcode.com/problems/rotate-list/
 */
public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k < 0) {
            return head;
        }
        int listLength = 1;
        ListNode tailNode = head;
        while (tailNode.next != null) {
            listLength++;
            tailNode = tailNode.next;
        }
        tailNode.next = head;
        int newHeadIndex = listLength - k % listLength;
        for (int i = 0; i < newHeadIndex; i++) {
            tailNode = tailNode.next;
        }
        ListNode newHead = tailNode.next;
        tailNode.next = null;
        return newHead;
    }
}
/* EOF */