package io.ziheng.list.leetcode;

import io.ziheng.list.leetcode.ListNode;

/**
 * LeetCode 876. Middle of the Linked List
 * https://leetcode.com/problems/middle-of-the-linked-list/
 */
public class MiddleOfTheLinkedList {
    public ListNode middleNode(ListNode head) {
        ListNode slowPointer = head;
        ListNode fastPointer = head;
        while (fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }
        return slowPointer;
    }
}
/* EOF */